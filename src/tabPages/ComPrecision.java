package tabPages;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ComPrecision extends Composite{
	
	/**
     * a text show the number of unique user
     */
    private Text userNumText;    
    
    /**
     * a text show the num of docs in the file
     */
    private Text docNumText;
    
    /**
     * a text shows the num of answers in the files
     */
    private Text ansNumText;
    
    /**
     * a text shows the num of answers to the current question
     */
    private Text AnsNumText;
    
    /**
     * represent the index of the doc
     */
    private int docIndex = 1;
    
    /**
     * a label that shows the index of the present doc 
     */
    private Label labelPresentDoc;
    
    /**
     * input the doc index that you want to go to
     */
    private Text textTurn2Doc;
    
    /**
     * show the content of the question
     */
    private Text textQuestionDetail;
    
    private Combo comboModelType;
    
    private Text textPredictAns;
    
    private Text textPredictExpert;
    
    private Text textOriginalAns;
    
    private Text textOriginalExpert;
    
//    /**
//     * the child node of the xml file 
//     */
//    private static String[] CHILDREN = 
//    	{"问题标题","问题内容","提问者用户名","提问者性别","提问者年龄","提问时间",
//    	"回复人1姓名", "回复人1职称", "回复人1分析",  "回复人1回复时间",
//    	"回复人2姓名", "回复人2职称", "回复人2分析",  "回复人2回复时间",
//    	"回复人3姓名", "回复人3职称", "回复人3分析",  "回复人3回复时间",
//    	"回复人4姓名", "回复人4职称", "回复人4分析",  "回复人4回复时间",
//    	"回复人5姓名", "回复人5职称", "回复人5分析",  "回复人5回复时间"};
    
    /**
     * the child mode of the map
     * "回复人1分析", "回复人2分析","回复人3分析","回复人4分析", "回复人5分析"
     */
    private static String[] CHILDREN2 = 
    	{"回复人1分析", "回复人2分析","回复人3分析","回复人4分析", "回复人5分析"};
    
    
    /**
     * the child mode of the map
     * "回复人1姓名", "回复人2姓名","回复人3姓名","回复人4姓名", "回复人5姓名"
     */
    private static String[] CHILDREN = 
    	{"回复人1姓名", "回复人2姓名","回复人3姓名","回复人4姓名", "回复人5姓名"};
    
    
	private final String[] MODELTYPE = {"Model 1", "Model 2"};
    
    
	
	public ComPrecision(Shell shell, Composite parent, int style){
		super(parent, style);  
		
		Group groupDocQuestion = new Group(this, SWT.BORDER);
		groupDocQuestion.setText("训练文档的问题");
		groupDocQuestion.setBounds(10, 10, 660, 150);
		
		Group groupPresentQuestion = new Group(groupDocQuestion, SWT.BORDER);
		groupPresentQuestion.setText("问题详情");
		groupPresentQuestion.setBounds(5, 5, 330, 125);
		
		labelPresentDoc = new Label(groupPresentQuestion, SWT.BORDER);
		labelPresentDoc.setText("当前第 x 条");
//        labelPresentDoc.setText("当前第" + docIndex +" 条");
        labelPresentDoc.setBounds(5, 8, 80, 15);
        
        Button buttonFrontDoc = new Button(groupPresentQuestion, SWT.BORDER);
        buttonFrontDoc.setText("Front");
        buttonFrontDoc.setBounds(75, 8, 60, 20);
        buttonFrontDoc.setBackground(new Color(null,245,245,245));
        buttonFrontDoc.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				goFrontPage();
			}
		});
        
        Button buttonNextDoc = new Button(groupPresentQuestion, SWT.BORDER);
        buttonNextDoc.setText("Next");
        buttonNextDoc.setBounds(130, 8, 60, 20);
        buttonNextDoc.setBackground(new Color(null,245,245,245));
        buttonNextDoc.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				goNextPage();
			}
		});
        
        Label labelTurn2Doc = new Label(groupPresentQuestion, SWT.BORDER);
        labelTurn2Doc.setText("转到第");
        labelTurn2Doc.setBounds(190, 8, 40, 15);
        
        textTurn2Doc = new Text(groupPresentQuestion, SWT.BORDER);
        textTurn2Doc.setBounds(230, 8, 30, 20);
        
        Button buttonGoDoc = new Button(groupPresentQuestion, SWT.BORDER);
        buttonGoDoc.setBounds(260, 8, 45, 20);
        buttonGoDoc.setText("Go");
        buttonGoDoc.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e){
        		goPage();
        	}
        });
        
        textQuestionDetail = new Text(groupPresentQuestion, SWT.BORDER | SWT.READ_ONLY |SWT.V_SCROLL | SWT.WRAP);
        textQuestionDetail.setBackground(new Color(null,225,225,225));
        textQuestionDetail.setBounds(5, 30, 315, 75);

		
		Group groupDocDetail = new Group(groupDocQuestion, SWT.BORDER);
		groupDocDetail.setText("文档信息");
		groupDocDetail.setBounds(350, 5, 300, 90);
		
		userNumText = new Text(groupDocDetail, SWT.BORDER | SWT.READ_ONLY);
        userNumText.setBounds(10, 10, 130, 22);
        userNumText.setBackground(new Color(null,245,245,245));
        userNumText.setText("不同答者数：");
        
        docNumText = new Text(groupDocDetail, SWT.BORDER | SWT.READ_ONLY);
        docNumText.setBounds(150, 10, 130, 22);
        docNumText.setBackground(new Color(null,245,245,245));
        docNumText.setText("问题数：");
        
        ansNumText = new Text(groupDocDetail, SWT.BORDER | SWT.READ_ONLY);
        ansNumText.setBounds(10, 40, 130, 22);
        ansNumText.setBackground(new Color(null,245,245,245));
        ansNumText.setText("回答数：");
        
        AnsNumText= new Text(groupDocDetail, SWT.BORDER | SWT.READ_ONLY);
        AnsNumText.setBounds(150, 40, 130, 22);
        AnsNumText.setBackground(new Color(null,245,245,245));
        AnsNumText.setText("当前问题回答数：");
		
        Label labelModelType = new Label(groupDocQuestion, SWT.BORDER);
		labelModelType.setText("请选择 Model 类型：");
		labelModelType.setBounds(350, 100, 120, 20);
		
		comboModelType = new Combo(groupDocQuestion, SWT.READ_ONLY);
		comboModelType.setBounds(470, 100, 80, 20);
		comboModelType.setItems(MODELTYPE);

		Group groupAnswer = new Group(this, SWT.BORDER);
		groupAnswer.setText("答案相关");
		groupAnswer.setBounds(10,170,660,230);
		
		Group groupPredict = new Group(groupAnswer, SWT.BORDER);
		groupPredict.setText("匹配内容");
		groupPredict.setBounds(5, 5, 320, 205);
		
		Group groupPredictAns = new Group(groupPredict, SWT.BORDER);
		groupPredictAns.setText("匹配答案");
		groupPredictAns.setBounds(5, 5, 308, 120);
		
		textPredictAns = new Text(groupPredictAns, SWT.BORDER | SWT.READ_ONLY |SWT.V_SCROLL | SWT.WRAP);
		textPredictAns.setBounds(5, 3, 295, 95);
		textPredictAns.setBackground(new Color(null,230,230,230));
		
		Group groupPredictExpert = new Group(groupPredict, SWT.BORDER);
		groupPredictExpert.setText("匹配专家");
		groupPredictExpert.setBounds(5, 125, 308, 60);
		
		textPredictExpert = new Text(groupPredictExpert, SWT.BORDER | SWT.READ_ONLY |SWT.V_SCROLL | SWT.WRAP);
		textPredictExpert.setBounds(5, 3, 295, 35);
		textPredictExpert.setBackground(new Color(null,230,230,230));
		
		Group groupOriginal = new Group(groupAnswer, SWT.BORDER);
		groupOriginal.setText("原有内容");
		groupOriginal.setBounds(330, 5, 320, 205);
		
		Group groupOriginalAns = new Group(groupOriginal, SWT.BORDER);
		groupOriginalAns.setText("采纳答案");
		groupOriginalAns.setBounds(5, 5, 308, 120);
		
		textOriginalAns = new Text(groupOriginalAns, SWT.BORDER | SWT.READ_ONLY |SWT.V_SCROLL | SWT.WRAP);
		textOriginalAns.setBounds(5, 3, 295, 95);
		textOriginalAns.setBackground(new Color(null,230,230,230));
		
		Group groupOriginalExpert = new Group(groupOriginal, SWT.BORDER);
		groupOriginalExpert.setText("采纳专家");
		groupOriginalExpert.setBounds(5, 125, 308, 60);
		
		textOriginalExpert = new Text(groupOriginalExpert, SWT.BORDER | SWT.READ_ONLY |SWT.V_SCROLL | SWT.WRAP);
		textOriginalExpert.setBounds(5, 3, 295, 35);
		textOriginalExpert.setBackground(new Color(null,230,230,230));
		
		Button buttonComfirmModel = new Button(groupDocQuestion, SWT.BORDER);
		buttonComfirmModel.setText("确认");
		buttonComfirmModel.setBounds(560, 95, 90, 32);
		buttonComfirmModel.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e){
        		getResult();
        	}
        });
		
		Button buttonGetAccuracy = new Button(this, SWT.BORDER);
		buttonGetAccuracy.setText("获取正确率");
		buttonGetAccuracy.setBounds(200,420, 100, 35);
		buttonGetAccuracy.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e){
        		getAccuracy();
        	}
        });
		
	}
	
	/**
	 * click the accuracy button and get the accuracy
	 */
	private void getAccuracy(){
		String comboString = comboModelType.getText();
		if (comboString.equals(MODELTYPE[0])){
			// do as the model 1
			getAccuracyModel1();
			getRandomAccuracy();
		}
		else if(comboString.equals(MODELTYPE[1])){
			// do as the model 2
			getAccuracyModel2();
		}
		else{
			MessageBox messagebox=new MessageBox(getShell(),SWT.YES|SWT.ICON_ERROR);
			messagebox.setText("Error");
			messagebox.setMessage("请先选择 Model 类型!");
			messagebox.open();					
		}
	}
	
	private void getAccuracyModel1(){
		int docNum = ComPreprocess.docMapMap.size();
		int countCorrect = 0 ;
		int predictIndex;
		for(int i = 1; i < docNum + 1; i++){
			predictIndex = getResultModel1(i);
			if(predictIndex == 0){
				countCorrect += 1;
			}
		}
		double accuracy = countCorrect * 1.0 / docNum;
		System.out.println("推荐答案的正确率:" + accuracy);
	}
	
	private void getRandomAccuracy(){
		int docNum = ComPreprocess.docMapMap.size();
		int ansNum;
		double randomCorrectProb = 0.0;
		for(Map.Entry<Integer, Map<String, String>> entry : ComPreprocess.docMapMap.entrySet()){
			ansNum = 0;
			for(int i = 0; i < CHILDREN2.length;i ++){
				if(entry.getValue().get(CHILDREN2[i]).length() != 0){
					ansNum += 1;
				}
			}
			randomCorrectProb += 1.0 / ansNum;
		}
		double randomCorrect = randomCorrectProb / docNum;
		System.out.println("答案的随机正确率:" + randomCorrect);

	}

	private void getAccuracyModel2(){
		
	}
	
	/**
	 * click the "comfirm" button and choose which the type of model
	 */
	private void getResult(){
		String comboString = comboModelType.getText();
//		System.out.println(comboString);
		if (comboString.equals(MODELTYPE[0])){
			// do as the model 1
			int predictIndex = getResultModel1(docIndex);
			textPredictAns.setText(ComPreprocess.docMapMap.get(docIndex).get(CHILDREN2[predictIndex]));
			textOriginalAns.setText(ComPreprocess.docMapMap.get(docIndex).get(CHILDREN2[0]));
		}
		else if(comboString.equals(MODELTYPE[1])){
			// do as the model 2
			getResultModel2();
		}
		else{
			MessageBox messagebox=new MessageBox(getShell(),SWT.YES|SWT.ICON_ERROR);
			messagebox.setText("Error");
			messagebox.setMessage("请先选择 Model 类型!");
			messagebox.open();					
		}
	}
	
	/**
	 * get the best answer for Model 1 among the answers the question provide
	 */
	private int getResultModel1(int index){
		String segSentense;
		String childNode;
		String[] words;
		int wordID;	
		double scoreProduct, scoreSum;
//		double sum = 0.0;
		int k;
		double bestAnsProb = 0.0;
		int bestAnsI = 0;
		
		for(int i = 0; i < CHILDREN2.length; i++){
			childNode = CHILDREN2[i];
			segSentense = ComPreprocess.segDocMapMap.get(index).get(childNode);
			
			if(segSentense.length() != 0){
				words = segSentense.split(" ");
				
				scoreProduct = 0.0;
				
				for(String word : words){
					if (!ComModel1.vocabularyAnswer.ifWordExist(word)){
						continue;
					}
					wordID = ComModel1.vocabularyAnswer.getId(word);
					scoreSum = 0.0;
					for (k = 0; k < ComModel1.topicNumAnswer; k++){
						scoreSum += ComModel1.phiAnswer[k][wordID] * ComModel1.thetaAnswer[index][k];
					}
					if (scoreProduct == 0.0 || scoreSum == 0.0){
						scoreProduct = scoreSum;
					}
					else{
						scoreProduct *= scoreSum;
					}					
				}
				
				if(scoreProduct > bestAnsProb){
					bestAnsProb = scoreProduct;
					bestAnsI = i;
				}				
			}
		}
		return bestAnsI;
	}
	
	/**
	 * get the best answer for Model 2 among the answers the question provide
	 */
	private void getResultModel2(){
		
	}
	
	/**
	 * update the doc index label
	 */
	private void updateDocIndex(){
		labelPresentDoc.setText("当前第 " + docIndex + " 条");
	}
	
	/**
	 * click the button and go to the front page and show in the text
	 */
	private void goFrontPage(){
		if(docIndex - 1 < 1){
			MessageBox messagebox=new MessageBox(getShell(),SWT.YES|SWT.ICON_ERROR);
			messagebox.setText("Error");
			messagebox.setMessage("已是第一页，无法向前!");
			messagebox.open();
		}
		else {
			docIndex -= 1;
			printDoc(docIndex);
			updateDocIndex();
		}		
	}
	
	/**
	 * click the button and go to the next page and show in the text
	 */
	private void goNextPage(){
		int docLength = ComPreprocess.docMapMap.size();
		if(docIndex + 1 > docLength){
			MessageBox messagebox=new MessageBox(getShell(),SWT.YES|SWT.ICON_ERROR);
			messagebox.setText("Error");
			messagebox.setMessage("已是最后一页，无法向后!");
			messagebox.open();
		}
		else{
			docIndex += 1;
			printDoc(docIndex);
			updateDocIndex();
		}
	}
	
	/**
	 * click the 'go' button and go to the certain page and show in the text
	 */
	private void goPage(){
		int docLength = ComPreprocess.docMapMap.size();
		int goIndex = Integer.parseInt(textTurn2Doc.getText());
		if (goIndex < 1 || goIndex > docLength){
			MessageBox messagebox=new MessageBox(getShell(),SWT.YES|SWT.ICON_ERROR);
			messagebox.setText("Error");
			messagebox.setMessage("无法转到此页!");
			messagebox.open();
		}
		else{
			docIndex = goIndex;
			printDoc(docIndex);
			updateDocIndex();
		}
	}
	
	private void printDoc(int docIndex){
		textQuestionDetail.setText("");
		String childText = "问题内容";
		String childContent;
		String inputText;
		childContent = ComPreprocess.docMapMap.get(docIndex).get(childText);
		if (childContent.length() != 0){
			inputText = "【" + childText +"】：" + childContent; 
			textQuestionDetail.append(inputText);
			textQuestionDetail.append("\n\n");
		}
		
		userNumText.setText("不同答者数：" + ComPreprocess.numExperts);
    	docNumText.setText("问题数：" + ComPreprocess.numQuestion);
    	ansNumText.setText("回答数：" + ComPreprocess.numAnswer);
    	AnsNumText.setText("当前问题回答数：" + getAnswerNum(docIndex));		
	}

	private int getAnswerNum(int questionIndex){
		String childText;
		String childContent;
		int numAnswer = 0;
		for (int i = 0; i < CHILDREN2.length; i++){
			childText = CHILDREN2[i];
			childContent = ComPreprocess.docMapMap.get(docIndex).get(childText);
			if (childContent.length() != 0){
				numAnswer += 1;
			}
		}				
		return numAnswer;
	}
	
	protected void checkSubclass() {  
        // Disable the check that prevents subclassing of SWT components  
    }  
	
}