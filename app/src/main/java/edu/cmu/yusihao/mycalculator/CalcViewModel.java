package edu.cmu.yusihao.mycalculator;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Stack;

import edu.cmu.cs.cs214.hw2.operator.AdditionOperator;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.DivisionOperator;
import edu.cmu.cs.cs214.hw2.operator.MultiplicationOperator;
import edu.cmu.cs.cs214.hw2.operator.Operator;
import edu.cmu.cs.cs214.hw2.operator.SubtractionOperator;
import android.widget.Toast;
import android.databinding.ObservableDouble;
import android.databinding.ObservableChar;


/**
 * Created by Hubert-Acer on 2016/9/21.
 */
public class CalcViewModel {
    private final Activity activity;
    private double bufferedNum;
    public final ObservableDouble displayNum = new ObservableDouble();
    private int decimalCount;
    private boolean decimalFlag;
    private boolean pushedOperator;
    private Stack<Double> operandStack;
    private Stack<BinaryOperator> operatorStack;
    private boolean equaled;
    public CalcViewModel(Activity activity) {
        bufferedNum = 0;
        displayNum.set(0);
        this.activity = activity;
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
        pushedOperator = false;
        decimalFlag = false;
        equaled = false;
    }
//    public double getDisplayNum(){
//
//        return displayNum.get();
//    }
    public void setDisplayNum(double num){
        Log.d("In setDspNum from",Double.toString(displayNum.get()));
        displayNum.set(num);
        Log.d("In setDspNum to",Double.toString(num));
    }
    public void showErrorMessage(String msg){

    }
    public String getOperatorStr(){
        if (operatorStack.isEmpty()){
            return "";
        }
        else{
            return operatorStack.peek().toString();
        }
    }
    public void addOperator(BinaryOperator opt){
        equaled = false;
        if (operatorStack.isEmpty()){
            operandStack.push(bufferedNum);
            Log.d("addOpt Pushed",Double.toString(bufferedNum));
            bufferedNum = 0;
            operatorStack.push(opt);
            Log.d("addOpt Pushed",opt.toString());
            pushedOperator = true;
        }
        else{
            //check for double operator
            if(pushedOperator){
                operatorStack.pop();
                operatorStack.push(opt);//last operator counts
            }
            else{
                if (operatorStack.peek().toString().equals("\u00F7") && bufferedNum == 0){
                    Toast.makeText(activity, "Division by 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                double arg2 = bufferedNum;
                double arg1 = operandStack.pop();
                double result = operatorStack.pop().apply(arg1,arg2);
                operandStack.push(result);
                setDisplayNum(result);
                operatorStack.push(opt);
                pushedOperator = true;
                bufferedNum = 0;
            }
        }
    }
    public void buttonClick(View vw){
        Log.d("CVM","buttonClicked");
        Log.d("BTX",((Button)vw).getText().toString());
        String buttonText = ((Button)vw).getText().toString();
        if (buttonText.equals("+")){
            BinaryOperator opt = new AdditionOperator();
            addOperator(opt);
        }
        else if (buttonText.equals("-")){
            BinaryOperator opt = new SubtractionOperator();
            addOperator(opt);
        }
        else if (buttonText.equals("\u00D7")){//multiply
            BinaryOperator opt = new MultiplicationOperator();
            addOperator(opt);
        }
        else if (buttonText.equals("\u00F7")){//divide
            BinaryOperator opt = new DivisionOperator();
            addOperator(opt);
        }
        else if (buttonText.equals("DEL")){
            bufferedNum = 0;
            displayNum.set(0);
            operandStack = new Stack<>();
            operatorStack = new Stack<>();
            pushedOperator = false;
            decimalFlag = false;
        }
        else if (buttonText.equals(".")){
            decimalFlag = true;
        }
        else if (buttonText.equals("=")){
            if (operandStack.isEmpty()){
                return; //do nothing
            }
            else{
                if (operatorStack.isEmpty()){
                    setDisplayNum(bufferedNum);
                }
                else{
                    if (operatorStack.peek().toString().equals("\u00F7") && bufferedNum == 0){
                        Toast.makeText(activity, "Division by 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double arg2 = bufferedNum;
                    double arg1 = operandStack.pop();
                    System.out.print("arg1:"+arg1+operatorStack.peek().toString()+"arg2"+arg2+"=");
                    double result = operatorStack.pop().apply(arg1,arg2);
                    System.out.println(result);
                    bufferedNum = result;
                    setDisplayNum(result);
                    pushedOperator = false;
                    equaled = true;
                }
            }
        }
        else {
            if (equaled){
                equaled = false;
                bufferedNum = 0;
            }
            int neg = bufferedNum>=0 ? 0: -1;
            if (buttonText.equals("0")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += 0;
                }
                else{
                    bufferedNum += 0*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                pushedOperator = false;
                setDisplayNum(bufferedNum);
                return;
            }
            else if (buttonText.equals("1")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*1;
                }
                else{
                    bufferedNum +=  Math.pow(-1,neg)*1*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("2")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*2;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*2*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("3")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*3;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*3*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("4")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*4;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*4*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("5")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*5;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*5*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("6")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*6;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*6*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("7")){
                Log.d("In 7",Double.toString(bufferedNum));
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*7;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*7*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("8")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*8;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*8*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
            else if (buttonText.equals("9")){
                if (! decimalFlag){
                    bufferedNum *= 10;
                    bufferedNum += Math.pow(-1,neg)*9;
                }
                else{
                    bufferedNum += Math.pow(-1,neg)*9*Math.pow(10,-decimalCount);
                    decimalCount += 1;
                }
                setDisplayNum(bufferedNum);
                pushedOperator = false;
            }
        }
    }
//    public final View.OnClickListener buttonClickListener = new View.OnClickListener(){
//        @Override
//        public void onClick(View vw){
//            String buttonText = ((Button)vw).getText().toString();
//            if (buttonText.equals("+")){
//                BinaryOperator opt = new AdditionOperator();
//                addOperator(opt);
//            }
//            else if (buttonText.equals("-")){
//                BinaryOperator opt = new SubtractionOperator();
//                addOperator(opt);
//            }
//            else if (buttonText.equals("\u00D7")){//multiply
//                BinaryOperator opt = new MultiplicationOperator();
//                addOperator(opt);
//            }
//            else if (buttonText.equals("\u00F7")){//divide
//                BinaryOperator opt = new DivisionOperator();
//                addOperator(opt);
//            }
//            else if (buttonText.equals("DEL")){
//                bufferedNum = 0;
//                displayNum = 0;
//                operandStack = new Stack<>();
//                operatorStack = new Stack<>();
//                pushedOperator = false;
//                decimalFlag = false;
//            }
//            else if (buttonText.equals(".")){
//                decimalFlag = true;
//            }
//            else if (buttonText.equals("=")){
//                if (operandStack.isEmpty()){
//                    return; //do nothing
//                }
//                else{
//                    if (operatorStack.isEmpty()){
//                        setDisplayNum(bufferedNum);
//                    }
//                    else{
//                        double arg2 = bufferedNum;
//                        double arg1 = operandStack.pop();
//                        double result = operatorStack.pop().apply(arg1,arg2);
//                        bufferedNum = 0;
//                        setDisplayNum(result);
//                    }
//                }
//            }
//            else if (buttonText.equals("0")){
//                if (bufferedNum == 0){
//                    return;
//                }
//                else{
//                    if (! decimalFlag){
//                        bufferedNum *= 10;
//                        bufferedNum += 0;
//                    }
//                    else{
//                        bufferedNum += 0*Math.pow(10,-decimalCount);
//                        decimalCount += 1;
//                    }
//                    pushedOperator = false;
//                    setDisplayNum(bufferedNum);
//                    return;
//                }
//            }
//            else if (buttonText.equals("1")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 1;
//                }
//                else{
//                    bufferedNum += 1*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("2")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 2;
//                }
//                else{
//                    bufferedNum += 2*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("3")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 3;
//                }
//                else{
//                    bufferedNum += 3*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("4")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 4;
//                }
//                else{
//                    bufferedNum += 4*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("5")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 5;
//                }
//                else{
//                    bufferedNum += 5*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("6")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 6;
//                }
//                else{
//                    bufferedNum += 6*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("7")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 7;
//                }
//                else{
//                    bufferedNum += 7*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("8")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 8;
//                }
//                else{
//                    bufferedNum += 8*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//            else if (buttonText.equals("9")){
//                if (! decimalFlag){
//                    bufferedNum *= 10;
//                    bufferedNum += 9;
//                }
//                else{
//                    bufferedNum += 9*Math.pow(10,-decimalCount);
//                    decimalCount += 1;
//                }
//                setDisplayNum(bufferedNum);
//                pushedOperator = false;
//            }
//
//        }
//    };

}
