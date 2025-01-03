package com.aventstack.customreports.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.bson.types.ObjectId;

import com.aventstack.customreports.CustomTest;
import com.aventstack.customreports.RunResult;
import com.aventstack.customreports.Status;
import com.aventstack.customreports.markuputils.Markup;

public class Log implements RunResult, Serializable, BasicReportElement {

    private static final long serialVersionUID = 1594512136869286425L;

    private AbstractStructure<ScreenCapture> screenCaptureContext;
    private AbstractStructure<Screencast> screencastContext;
    
    private CustomTest parent;
    private Test parentModel;
    private Markup markup;
    private Date timestamp;
    private Status logStatus;
    private String stepName;
    private String details = "";
    private int sequence;
    private ObjectId objectId;
    
    private Log() {
    	timestamp = Calendar.getInstance().getTime();
    }
    
    public Log(Test test) { 
    	this();
    	this.parentModel = test;
    }
    
    public Log(CustomTest test) {
    	this();
    	this.parent = test;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(Status logStatus) {
        this.logStatus = logStatus;
    }
    public Status getStatus() {
        return logStatus;
    }
   
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }
    public String getStepName() {
        return stepName;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getDetails() {
        return details;
    }
    
    public void setMarkup(Markup markup) {
        this.markup = markup;
    }
    public Markup getMarkup() {
        return markup;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    public int getSequence() {
        return sequence;
    }
    
    public void setScreenCapture(ScreenCapture sc) {
        if (screenCaptureContext == null)
            screenCaptureContext = new AbstractStructure<>();
        
        screenCaptureContext.add(sc);
        
        String details = getDetails().isEmpty() 
        		? sc.getSource() 
				: getDetails() + sc.getSource();
        setDetails(details);
        sc.setTestObjectId(getParent().getModel().getObjectId());
    }
    public AbstractStructure<ScreenCapture> getScreenCaptureContext() {
        return screenCaptureContext;
    }
    
    public boolean hasScreenCapture() {
        return screenCaptureContext != null 
                && screenCaptureContext.size() > 0;
    }
    
    public void setScreencast(Screencast sc) {
    	if (screencastContext == null)
    		screencastContext = new AbstractStructure<>();
    	
    	screencastContext.add(sc);
    	
    	String details = getDetails().isEmpty()
    			? sc.getSource()
				: getDetails() + sc.getSource();
		setDetails(details);
    }
    public AbstractStructure<Screencast> getScreencastContext() {
    	return screencastContext;
    }
    
    public CustomTest getParent() {
        return parent;
    }
    
    public Test getParentModel() {
    	if (parent == null)
    		return parentModel;
    		
        return parent.getModel();
    }
    
    @Override
    public ObjectId getObjectId() {
        return objectId;
    }

    @Override
    public void setObjectId(ObjectId id) {
        objectId = id;
    }
    
}
