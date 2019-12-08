package scientific.calculator;

import com.google.common.math.DoubleMath;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;


public class MainController {
    
	@FXML
	private MenuItem exit;
	@FXML
	private MenuItem about;
    @FXML
    private Label text1;
    @FXML
    private Label text2;
    @FXML
    private RadioButton rbDegree;
    @FXML
    private RadioButton rbRadian;
    @FXML
    private RadioButton rbGrad;
    private double number1=0.0;
    private String operator = "";
    private boolean start = true;
    
    @FXML
    void aboutWindow(ActionEvent event) {
    	Stage aboutWindow = new Stage();
    	Label lblAbout = new Label(
    				" This project, the Scientific Calculator, "
    				+ "\n\n was made by Tyler (Project Manager), "
    				+ "\n\n Anthony, and Nick.");
    	lblAbout.setLayoutX(25); lblAbout.setLayoutY(25);
    	Group grp = new Group(lblAbout);
    	Scene sn = new Scene(grp, 300, 200);
    	aboutWindow.setScene(sn);
    	aboutWindow.setTitle("About Window");
    	aboutWindow.show();	
    }
    
    @FXML
    void exitWindow(ActionEvent event) {
    	System.exit(0);
    }
    
    @FXML
    public void processNumbers(ActionEvent event){
        if(start){
            text1.setText("0");
            start = false;
        }
        
        String value = ((Button)event.getSource()).getText();
        if(value.equals("0") && text1.getText().equals("0"))
            return;
        if(text1.getText().equals("0"))
            text1.setText(value);
        else
            text1.setText(text1.getText()+value);
    }
    
    @FXML
    public void appendDot(ActionEvent event){
         if(start){
            text1.setText("0");
            start = false;
        }
         
        if(!text1.getText().contains("."))
            text1.setText(text1.getText()+".");
        start = false;
    }
    
    public double applyOperator(double num1, double num2, String op){
        switch(op){
            case "+":{
                return num1+num2;
            }
            case "-":{
                return num1-num2;
            }
            case "*":{
                return num1*num2;
            }
            case "/":{
                if(num2==0){
                    return 0;
                }
                return num1/num2;
            }
            case "^":{
                return Math.pow(num1, num2);
            }
            case "Mod":{
                return num1%num2;
            }
            case "Exp":{
                return num1 * Math.pow(Math.E, num2);
            }
        }
        return 0;
    }
    
    @FXML
    public void processOperators(ActionEvent event){
        String op = ((Button)event.getSource()).getText();
        
        if (op.equals("=")){
            if(!operator.equals("")){
                double number2 = Double.parseDouble(text1.getText());
                number1 = applyOperator(number1, number2, operator);
            }
            operator = "";
            text2.setText("");
        }else{
            if(!operator.equals("")){
                double number2 = Double.parseDouble(text1.getText());
                number1 = applyOperator(number1, number2, operator);
            }else{
                number1 = Double.parseDouble(text1.getText());
            }
            operator = op;
            text2.setText(number1 + " " + op);
        }
        text1.setText(number1+"");
        start = true;
    }
    
    @FXML
    public void clearInput(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        
        if(value.equals("C")){
            text1.setText("0");
            text2.setText("");
        }else{
            text1.setText("0");
        }
    }
    
    @FXML
    public void changeRadioState(ActionEvent event){
        String op = ((RadioButton)event.getSource()).getText();
        
        switch (op) {
            case "DEG":
                rbDegree.setSelected(true);
                rbRadian.setSelected(false);
                rbGrad.setSelected(false);
                break;
            case "RAD":
                rbDegree.setSelected(false);
                rbRadian.setSelected(true);
                rbGrad.setSelected(false);
                break;
            default:
                rbDegree.setSelected(false);
                rbRadian.setSelected(false);
                rbGrad.setSelected(true);
                break;
        }
    }
    
    public double getRadianValue(double number){
        if(rbDegree.isSelected()){
            return Math.toRadians(number);
        }else if(rbGrad.isSelected()){
            return Math.toRadians(Math.PI * number / 200);
        }
        
        return number;
    }
    
    @FXML
    public void evaluateTrigonometricFunctions(ActionEvent event){
        String op = ((Button)event.getSource()).getText();
        number1 = Double.parseDouble(text1.getText());
        double number = getRadianValue(number1);
        double result = 0.0;
       
        switch(op){
            case "sin":{
                result = Math.sin(number);
                text2.setText(text2.getText()+"sin("+number1+")");
                break;
            }
            case "cos":{
                result = Math.cos(number);
                text2.setText(text2.getText()+"cos("+number1+")");
                break;
            }
            case "tan":{
                result = Math.tan(number);
                text2.setText(text2.getText()+"tan("+number1+")");
                break;
            }
            case "sinh":{
                result = Math.sinh(number);
                text2.setText(text2.getText()+"sinh("+number1+")");
                break;
            }
            case "cosh":{
                result = Math.cosh(number);
                text2.setText(text2.getText()+"cosh("+number1+")");
                break;
            }
            case "tanh":{
                result = Math.tanh(number);
                text2.setText(text2.getText()+"tanh("+number1+")");
                break;
            }
        }
        text1.setText(result+"");
        start = true;
    }
    
    @FXML
    public void applyUnaryFucntions(ActionEvent event){
        String op = ((Button)event.getSource()).getText();
        number1 = Double.parseDouble(text1.getText());
        double result = 0.0;
        
        switch(op){
            case "1/x":{
                if(number1==0){
                    text1.setText("Invalid Input");
                    start = true;
                    return;
                }
                result = 1.0/number1;
                text2.setText("1/"+number1);
                number1 = 1.0/number1;
                break;
            }
            case "sqrt":{
                if(number1<0){
                    text1.setText("Invalid Input");
                    start = true;
                    return;
                }
                result = Math.sqrt(number1);
                text2.setText(text2.getText()+"sqrt("+number1+")");
                break;
            }
            case "x^2":{
                result = Math.pow(number1, 2);
                text2.setText(text2.getText()+number1+"^2");
                break;
            }
            case "x^5":{
                result = Math.pow(number1, 5);
                text2.setText(text2.getText()+number1+"^5");
                break;
            }
            case "10^x":{
                result = Math.pow(10, number1);
                text2.setText(text2.getText()+"10^"+number1);
                break;
            }
            case "log":{
                result = Math.log10(number1);
                text2.setText(text2.getText()+"10^"+number1);
                break;
            }
            case "ln":{
                result = Math.log(number1);
                text2.setText(text2.getText()+"10^"+number1);
                break;
            }
            case "n!":{
                result = DoubleMath.factorial((int)(number1));
                text2.setText(text2.getText()+"fact("+number1+")");
                break;
            }
        }
        text1.setText(result+"");
        start = true;
    }
    
    
}
