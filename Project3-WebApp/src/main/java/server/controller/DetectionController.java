package server.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.FaceAffectiveData;
import model.FaceData;
import model.FaceExpressionData;

/**
 * Controller for the detection section of the server
 * 
 * @SER516 SER516_ExtraCredit
 * @Version 1.0
 */
@SuppressWarnings("rawtypes")
public class DetectionController {

	public JSpinner spinnerEmoStateInterval, spinnerUpperFace, spinnerLowerFace, spinnerAffective;
	public JCheckBox checkboxEyeAutoReset;
	public JComboBox comboUpperFace, comboLowerFace, comboEye, comboAffective;
	public JRadioButton radioEyeActive;
	public FaceAffectiveData faceAffectiveData;
	public FaceExpressionData faceExpressionData;
	public JTextArea textAreaEmoLogs;
	public JButton buttonClearLogs;
	
	
	
	/**
	 * Controller to detect server options
	 * 
	 * @param spinnerUpperFace
	 * @param spinnerLowerFace
	 * @param spinnerAffective
	 * @param comboUpperFace
	 * @param comboLowerFace
	 * @param comboAffective
	 * @param comboEye
	 * @param checkEyeBox
	 * @param radioEyeActive
	 * @param txtAreaEmoLogs
	 * @param btnClearLogs
	 * @param faceAffectiveData
	 * @param faceExpressionData
	 */

	private DetectionController(final JSpinner spinnerUpperFace, final JSpinner spinnerLowerFace,
			final JSpinner spinnerAffective, final JComboBox comboUpperFace, final JComboBox comboLowerFace,
			final JComboBox comboAffective, final JComboBox comboEye, final JCheckBox checkEyeBox,
			final JRadioButton radioEyeActive,final JTextArea txtAreaEmoLogs,final JButton btnClearLogs, FaceAffectiveData faceAffectiveData,
			FaceExpressionData faceExpressionData) {

		this.faceAffectiveData = faceAffectiveData;
		this.faceExpressionData = faceExpressionData;
		this.spinnerAffective = spinnerAffective;
		this.spinnerLowerFace = spinnerLowerFace;
		this.spinnerUpperFace = spinnerUpperFace;
		this.comboUpperFace = comboUpperFace;
		this.comboLowerFace = comboLowerFace;
		this.comboAffective = comboAffective;
		this.comboEye = comboEye;
		this.radioEyeActive = radioEyeActive;
		this.checkboxEyeAutoReset = checkEyeBox;
		this.textAreaEmoLogs = txtAreaEmoLogs;
		this.buttonClearLogs = btnClearLogs;
		
	
		
		comboUpperFace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExpressionData();
			}
		});

		comboLowerFace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExpressionData();
			}
		});

		comboAffective.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAffectiveData();
			}
		});

		comboEye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExpressionData();
			}
		});

		checkboxEyeAutoReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExpressionData();
			}
		});

		radioEyeActive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExpressionData();
			}
		});

		spinnerUpperFace.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateExpressionData();
			}
		});

		spinnerLowerFace.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateExpressionData();
			}
		});

		spinnerAffective.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateAffectiveData();
			}
		});
		
		btnClearLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAreaEmoLogs.setText("");
			}
		});

	}
	
	public DetectionController() {
		
	}

	/**
	 * Updates the expression data based on the selected spinner values.
	 */
	public void updateExpressionData() {

		faceExpressionData.reset();
		String upperFace = (String) comboUpperFace.getSelectedItem();
		Double upperFaceValue = Double.parseDouble((String) spinnerUpperFace.getValue());

		switch (upperFace.toString()) {
		case "Raise Brow":
			faceExpressionData.setRaiseBrow(upperFaceValue);
			break;
		case "Furrow Brow":
			faceExpressionData.setFurrowBrow(upperFaceValue);
			break;
		}

		String lowerFace = (String) comboLowerFace.getSelectedItem();
		Double lowerFaceValue = Double.parseDouble((String) spinnerLowerFace.getValue());

		switch (lowerFace.toString()) {
		case "Smile":
			faceExpressionData.setSmile(lowerFaceValue);
			break;
		case "Clench":
			faceExpressionData.setClench(lowerFaceValue);
			break;
		case "Smirk Left":
			faceExpressionData.setSmirkLeft(lowerFaceValue);
			break;
		case "Smirk Right":
			faceExpressionData.setSmirkRight(lowerFaceValue);
			break;
		case "Laugh":
			faceExpressionData.setLaugh(lowerFaceValue);
			break;
		}

		boolean eyeActive = radioEyeActive.isSelected();

		if (eyeActive) {
			String eye = (String) comboEye.getSelectedItem();

			switch (eye) {
			case "Blink":
				faceExpressionData.setBlink(1.0);
				break;
			case "Wink Left":
				faceExpressionData.setWinkLeft(1.0);
				break;
			case "Wink Right":
				faceExpressionData.setWinkRight(1.0);
				break;
			case "Look Left":
				faceExpressionData.setLookLeft(1.0);
				break;
			case "Look Right":
				faceExpressionData.setLookRight(1.0);
				break;
			}
		}

		boolean eyeAutoReset = checkboxEyeAutoReset.isSelected();

		if (eyeAutoReset) {
			faceExpressionData.setEyeReset(true);
		}
	}
	
	/**
	 * Updates the affective data based on the selected spinner values.
	 */
	public void updateAffectiveData() {

		faceAffectiveData.reset();

		String affective = (String) comboAffective.getSelectedItem();
		Double affectiveValue = Double.parseDouble((String) spinnerAffective.getValue());

		switch (affective) {
		case "Meditation":
			faceAffectiveData.setMeditation(affectiveValue);
			break;
		case "Engagement Boredom":
			faceAffectiveData.setEngagementBoredom(affectiveValue);
			break;
		case "Excitement ShortTerm":
			faceAffectiveData.setExcitementShortTerm(affectiveValue);
			break;
		case "Frustration":
			faceAffectiveData.setFrustation(affectiveValue);
			break;
		case "Excitement LongTerm":
			faceAffectiveData.setExcitementLongTerm(affectiveValue);
			break;
		}
	}
	
	/**
	 * Passes on the latest data to the model classes.
	 * @return returns the combined expression and affective data.
	 */
	public FaceData createFaceDataInstance() {
		FaceData faceData = new FaceData();
		faceData.setFaceAffectiveData(faceAffectiveData);
		faceData.setFaceExpressionData(faceExpressionData);
		return faceData;
		
		
	}
	/**
	 * 
	 * @Inner class builder
	 *
	 */
	 
		public class DetectionControllerBuilder
		{
			/**
			 * Constructor to build the control detection class
			 * 
			 * @param spinnerUpperFacebuilder
			 * @param spinnerLowerFacebuilder
			 * @param spinnerAffectivebuilder
			 * @param comboUpperFacebuilder
			 * @param comboLowerFacebuilder
			 * @param comboAffectivebuilder
			 * @param comboEyebuilder
			 * @param checkEyeBoxbuilder
			 * @param radioEyeActivebuilder
			 * @param txtAreaEmoLogsbuilder
			 * @param btnClearLogsbuilder
			 * @param faceAffectiveDatabuilder
			 * @param faceExpressionDatabuilder
			 */
			 
					public DetectionControllerBuilder(final JSpinner spinnerUpperFacebuilder, final JSpinner spinnerLowerFacebuilder,
					final JSpinner spinnerAffectivebuilder, final JComboBox comboUpperFacebuilder, final JComboBox comboLowerFacebuilder,
					final JComboBox comboAffectivebuilder, final JComboBox comboEyebuilder, final JCheckBox checkEyeBoxbuilder,
					final JRadioButton radioEyeActivebuilder,final JTextArea txtAreaEmoLogsbuilder,final JButton btnClearLogsbuilder, FaceAffectiveData faceAffectiveDatabuilder,
					FaceExpressionData faceExpressionDatabuilder)
			
		
			{
				faceAffectiveData = faceAffectiveDatabuilder;
				faceExpressionData = faceExpressionDatabuilder;
				spinnerAffective = spinnerAffectivebuilder;
				spinnerLowerFace = spinnerLowerFacebuilder;
				spinnerUpperFace = spinnerUpperFacebuilder;
				comboUpperFace = comboUpperFacebuilder;
				comboLowerFace = comboLowerFacebuilder;
				comboAffective = comboAffectivebuilder;
				comboEye = comboEyebuilder;
				radioEyeActive = radioEyeActivebuilder;
				checkboxEyeAutoReset = checkEyeBoxbuilder;
				textAreaEmoLogs = txtAreaEmoLogsbuilder;
				buttonClearLogs = btnClearLogsbuilder;
				
			}
			
					/**
					 *Creates a Detection Controllor
					 */
					
			public DetectionController createDetectionControl()
			{
				return new DetectionController( spinnerUpperFace,  spinnerLowerFace,
						spinnerAffective,  comboUpperFace,  comboLowerFace,
					 comboAffective, comboEye,  checkboxEyeAutoReset,
						 radioEyeActive,textAreaEmoLogs, buttonClearLogs, faceAffectiveData, faceExpressionData
						);
				
			}
			
		}
}
