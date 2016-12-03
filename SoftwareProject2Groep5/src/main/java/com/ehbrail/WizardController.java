package com.ehbrail;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import javafx.beans.binding.When;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WizardController implements Initializable {
	
	@FXML
	VBox contentPanel;

	@FXML
	HBox hboxIndicators;

	@FXML
	Button btnNext, btnBack, btnCancel;
	
	
	@FXML
	public void next() {

	 if( currentStep.get() < (steps.size()-1) ) {
	  contentPanel.getChildren().remove( steps.get(currentStep.get()) );
	  currentStep.set( currentStep.get() + 1 );
	  contentPanel.getChildren().add( steps.get(currentStep.get()) );
	 }
	}

	@FXML
	public void back() {

	 if( currentStep.get() > 0 ) {
	  contentPanel.getChildren().remove( steps.get(currentStep.get()) );
	  currentStep.set( currentStep.get() - 1 );
	  contentPanel.getChildren().add( steps.get(currentStep.get()) );
	 }
	}

	@FXML
	public void cancel() {

	 contentPanel.getChildren().remove( steps.get(currentStep.get()) );
	 currentStep.set( 0 );  // first screen
	 contentPanel.getChildren().add( steps.get(currentStep.get()) );
	}

	private final List<Parent> steps = new ArrayList< >();

	private final IntegerProperty currentStep = new SimpleIntegerProperty(-1);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			buildSteps();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 initButtons();
		 setInitialContent();
		
	}
	
	private void buildSteps() throws java.io.IOException {

		 final JavaFXBuilderFactory bf = new JavaFXBuilderFactory();
		 Parent step1 = FXMLLoader.load( WizardController.class.getResource("/com.ehbrail/NieuwKlantTab.fxml"), null, bf);
		 Parent step2 = FXMLLoader.load( WizardController.class.getResource("/com.ehbrail/wKlantAdres.fxml"), null, bf);
		
		 steps.addAll( Arrays.asList(
		   step1, step2
		    ));
		}
	
	private void initButtons() {
		 btnBack.disableProperty().bind( currentStep.lessThanOrEqualTo(0) );
		 btnNext.disableProperty().bind( currentStep.greaterThanOrEqualTo(steps.size()-1) );

		 btnCancel.textProperty().bind(
		  new When(
		    currentStep.lessThan(steps.size()-1)
		  )
		    .then("Cancel")
		    .otherwise("Start Over")
		 );
		}
	
	
	private void setInitialContent() {
		 currentStep.set( 0 );  // first element
		 contentPanel.getChildren().add( steps.get( currentStep.get() ));
		}

}
