package com.aventstack.customreports;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import com.aventstack.customreports.feature.model.IGherkinFormatterModel;
import com.aventstack.customreports.markuputils.Markup;
import com.aventstack.customreports.model.Author;
import com.aventstack.customreports.model.Category;
import com.aventstack.customreports.model.ExceptionInfo;
import com.aventstack.customreports.model.IAddsMedia;
import com.aventstack.customreports.model.Log;
import com.aventstack.customreports.model.Media;
import com.aventstack.customreports.model.MediaType;
import com.aventstack.customreports.model.ScreenCapture;
import com.aventstack.customreports.model.Screencast;
import com.aventstack.customreports.model.Test;
import com.aventstack.customreports.utils.ExceptionUtil;
import com.aventstack.customreports.utils.StringUtil;

/**
 * Defines a test. You can add logs, snapshots, assign author and categories to a test and its children.
 * 
 * <p>
 * The below log types will all be logged with <code>Status.PASS</code>:
 * </p>
 * 
 * <pre>
 * test.log(Status.PASS, "details");
 * test.pass("details");
 * test.pass(MarkupHelper.createCodeBlock(code));
 * </pre>
 * 
 * <p>
 * A few notes:
 * </p>
 * 
 * <ul>
 * 	<li>Tests started with the <code>createTest</code> method are parent-level, always level 0</li>
 * 	<li>Tests started with the <code>createNode</code> method are children, always level 1 and greater</li>
 * </ul>
 */
public class CustomTest implements IAddsMedia<CustomTest>, RunResult, Serializable {
    
    private static final long serialVersionUID = 9199820968410788862L;
    
    private CustomReports extent;
    private Test test;

    /**
     * Creates a BDD style parent test representing one of the {@link IGherkinFormatterModel}
     * classes. This method would ideally be used for creating the parent, ie {@link Feature). 
     * 
     * <ul>
     *  <li>{@link com.aventstack.customreports.feature.model.Feature}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Background}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Scenario}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Given}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.When}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Then}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * extent.createTest(Feature.class, "Feature Name", "Description");
     * </pre>
     * 
     * @param extent An {@link CustomReports} object
     * @param type A {@link IGherkinFormatterModel} type
     * @param testName Test name
     * @param description Test description
     */
    CustomTest(CustomReports extent, Class<? extends IGherkinFormatterModel> type, String testName, String description) {
        if (testName == null || testName.isEmpty())
            throw new IllegalArgumentException("testName cannot be null or empty");
        
        this.extent = extent;
        
        test = new Test();
        test.setName(testName.trim()); 
        test.setDescription(description == null ? "" : description.trim());
        
        if (type != null)
            test.setBehaviorDrivenType(type);
    }
    
    /**
     * Create a test with description
     * 
     * @param extent An {@link CustomReports} object
     * @param testName Test name
     * @param description Test description
     */
    CustomTest(CustomReports extent, String testName, String description) {
        this(extent, null, testName, description);
    }
    
    /**
     * Creates a BDD-style node with description representing one of the {@link IGherkinFormatterModel}
     * classes:
     * 
     * <ul>
     *  <li>{@link com.aventstack.customreports.feature.model.Feature}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Background}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Scenario}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Given}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.When}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Then}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(Scenario.class, "bddNode", "description");
     * </pre>
     * 
     * @param type A {@link IGherkinFormatterModel} type
     * @param name Name of node
     * @param description A short description
     * 
     * @return {@link CustomTest} object
     */
    public synchronized CustomTest createNode(Class<? extends IGherkinFormatterModel> type, String name, String description) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("nodeName cannot be null or empty");
        
        CustomTest t;
        if (type == null) {
            t = new CustomTest(extent, name, description);
        }
        else {
            t = new CustomTest(extent, type, name, description);
        }
        
        applyCommonNodeSettings(t);
        addNodeToReport(t);
                
        return t;
    }
    
    /**
     * Creates a node with description
     * 
     * @param name Name of node
     * @param description A short description
     * 
     * @return {@link CustomTest} object
     */
    public synchronized CustomTest createNode(String name, String description) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("nodeName cannot be null or empty");
        
        CustomTest t = new CustomTest(extent, name, description);
        applyCommonNodeSettings(t);
        addNodeToReport(t);
        
        return t;
    }

    /**
     * Creates a BDD-style node representing one of the {@link IGherkinFormatterModel} classes such as:
     * 
     * <ul>
     *  <li>{@link com.aventstack.customreports.feature.model.Feature}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Background}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Scenario}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Given}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.When}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Then}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(Scenario.class, "bddNode");
     * </pre>
     * 
     * @param type A {@link IGherkinFormatterModel} type
     * @param name Name of node
     * 
     * @return {@link CustomTest} object
     */
    public synchronized CustomTest createNode(Class<? extends IGherkinFormatterModel> type, String name) {
        return createNode(type, name, null);
    }
    
    /**
     * Creates a BDD-style node with description using name of the Gherkin model such as:
     * 
     * <ul>
     *  <li>{@link com.aventstack.customreports.feature.model.Feature}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Background}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Scenario}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Given}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.When}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Then}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(new GherkinKeyword("Scenario"), "bddTest", "description");
     * </pre>
     * 
     * @param gherkinKeyword Name of the {@link GherkinKeyword}
     * @param name Name of node
     * @param description A short description
     * 
     * @return {@link CustomTest}
     */
    public synchronized CustomTest createNode(GherkinKeyword gherkinKeyword, String name, String description) {
        Class<? extends IGherkinFormatterModel> clazz = gherkinKeyword.getKeyword().getClass();
        return createNode(clazz, name, description);
    }
    
    /**
     * Creates a BDD-style node using name of the Gherkin model such as:
     * 
     * <ul>
     *  <li>{@link com.aventstack.customreports.feature.model.Feature}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Background}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Scenario}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Given}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.When}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.Then}</li>
     *  <li>{@link com.aventstack.customreports.feature.model.And}</li>
     * </ul>
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.createNode(new GherkinKeyword("Scenario"), "bddTest");
     * </pre>
     * 
     * @param gherkinKeyword Name of the {@link GherkinKeyword}
     * @param name Name of node
     * 
     * @return {@link CustomTest} object
     */
    public synchronized CustomTest createNode(GherkinKeyword gherkinKeyword, String name) {
        return createNode(gherkinKeyword, name, null);
    }
    
    /**
     * Creates a node
     * 
     * @param name Name of node
     * 
     * @return {@link CustomTest} object
     */
    public synchronized CustomTest createNode(String name) {
        return createNode(name, null);
    }
    
    private void applyCommonNodeSettings(CustomTest customTest) {
        customTest.getModel().setLevel(test.getLevel() + 1);
        customTest.getModel().setParent(getModel());
        test.getNodeContext().add(customTest.getModel());
    }
    
    private void addNodeToReport(CustomTest extentNode) {
        extent.addNode(extentNode.getModel());
    }
    
    /**
     * Logs an event with {@link Status}, details and a media object: {@link Screencast} or
     * {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.log(Status.FAIL, "details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param status {@link Status}
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public synchronized CustomTest log(Status status, String details, MediaEntityModelProvider provider) {
    	Log evt = createLog(status, details);
    	
    	if (provider != null) {
	    	Class<? extends Media> clazz = provider.getMedia().getClass();
	    	
	    	if (clazz.equals(ScreenCapture.class))
	    		evt.setScreenCapture((ScreenCapture) provider.getMedia());
			else
				evt.setScreencast((Screencast) provider.getMedia());
    	}
    	
    	return addLog(evt);
    }
    
    /**
     * Logs an event with {@link Status} and details
     * 
     * @param status {@link Status}
     * @param details Details
     * 
     * @return An {@link CustomTest} object
     */
    public synchronized CustomTest log(Status status, String details) {       
        return log(status, details, null);
    }
    
    /**
     * Logs an event with {@link Status} and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param status {@link Status}
     * @param markup {@link Markup}
     * 
     * @return An {@link CustomTest} object
     */
    public synchronized CustomTest log(Status status, Markup markup) {
        String details = markup.getMarkup();
        return log(status, details);
    }
    
    private synchronized CustomTest addLog(Log evt) {
        test.getLogContext().add(evt);
        test.end();
        
        extent.addLog(test, evt);
        
        if (evt.hasScreenCapture()) {
            try {
                extent.addScreenCapture(evt, evt.getScreenCaptureContext().getLast());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return this;
    }
    
    private Log createLog(Status status) {
        Log evt = new Log(this);
        evt.setStatus(status);
        evt.setSequence(test.getLogContext().getAll().size() + 1);
        
        return evt;
    }
    
    private Log createLog(Status status, String details) {
        Log evt = createLog(status);
        evt.setDetails(details == null ? "" : details.trim());
        
        return evt;
    }

    /**
     * Logs an event with {@link Status}, an exception and a media object: {@link Screencast} or
     * {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.log(Status.FAIL, exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param status {@link Status}
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public synchronized CustomTest log(Status status, Throwable t, MediaEntityModelProvider provider) {
        ExceptionInfo exInfo = new ExceptionInfo();
        exInfo.setException(t);
        exInfo.setExceptionName(ExceptionUtil.getExceptionHeadline(t));
        exInfo.setStackTrace(ExceptionUtil.getStackTrace(t));
        
        getModel().setExceptionInfo(exInfo);

        log(status, exInfo.getStackTrace(), provider);
        
        return this;
    }
    
    /**
     * Logs an event with {@link Status} and exception
     * 
     * @param status {@link Status}
     * @param t {@link Throwable}
     * 
     * @return An {@link CustomTest} object
     */
    public synchronized CustomTest log(Status status, Throwable t) {
        return log(status, t, null);
    }
    
    /**
     * Logs an <code>Status.INFO</code> event with details and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.info("details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param details Details 
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest info(String details, MediaEntityModelProvider provider) {
    	log(Status.INFO, details, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.INFO</code> with details
     * 
     * @param details Details
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest info(String details) {
        return info(details, null);
    }
    
    /**
     * Logs an <code>Status.INFO</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
	 * test.info(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest info(Throwable t, MediaEntityModelProvider provider) {
    	log(Status.INFO, t, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.INFO</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest info(Throwable t) {
        return info(t, null);
    }
    
    /**
     * Logs an event with <code>Status.INFO</code> and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest info(Markup m) {
        log(Status.INFO, m);
        return this;
    }
    
    /**
     * Logs an <code>Status.PASS</code> event with details and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest pass(String details, MediaEntityModelProvider provider) {
    	log(Status.PASS, details, provider);
    	return this;
    }
    
    /**
     * Logs an event <code>Status.PASS</code> with details
     * 
     * @param details Details
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest pass(String details) {
        return pass(details, null);
    }
    
    /**
     * Logs an <code>Status.PASS</code> event with an exception and a media object:
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
	 * test.pass(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest pass(Throwable t, MediaEntityModelProvider provider) {
    	log(Status.PASS, t, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.PASS</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest pass(Throwable t) {
        return pass(t, null);
    }
    
    /**
     * Logs an event with <code>Status.PASS</code> and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest pass(Markup m) {
        log(Status.PASS, m);
        return this;
    }
    
    /**
     * Logs an <code>Status.FAIL</code> event with details and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest fail(String details, MediaEntityModelProvider provider) {
    	log(Status.FAIL, details, provider);
    	return this;
    }
    
    /**
     * Logs an event <code>Status.FAIL</code> with details
     * 
     * @param details Details
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest fail(String details) {
        return fail(details, null);
    }
    
    /**
     * Logs an <code>Status.FAIL</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
	 * test.fail(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest fail(Throwable t, MediaEntityModelProvider provider) {
    	log(Status.FAIL, t, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.FAIL</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest fail(Throwable t) {
        return fail(t, null);
    }
    
    /**
     * Logs an event with <code>Status.FAIL</code> and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest fail(Markup m) {
        log(Status.FAIL, m);
        return this;
    }
    
    /**
     * Logs an <code>Status.DATAL</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest fatal(String details, MediaEntityModelProvider provider) {
    	log(Status.FATAL, details, provider);
    	return this;
    }
    
    /**
     * Logs an event <code>Status.FATAL</code> with details
     * 
     * @param details Details
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest fatal(String details) {
        log(Status.FATAL, details);
        return this;
    }
    
    /**
     * Logs an <code>Status.FATAL</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
	 * test.fatal(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest fatal(Throwable t, MediaEntityModelProvider provider) {
    	log(Status.FATAL, t, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.FATAL</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest fatal(Throwable t) {
        log(Status.FATAL, t);
        return this;
    }
    
    /**
     * Logs an event with <code>Status.FATAL</code> and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest fatal(Markup m) {
        log(Status.FATAL, m);
        return this;
    }
    
    /**
     * Logs an <code>Status.WARNING</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest warning(String details, MediaEntityModelProvider provider) {
    	log(Status.WARNING, details, provider);
    	return this;
    }
    
    /**
     * Logs an event <code>Status.WARNING</code> with details
     * 
     * @param details Details
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest warning(String details) {
        return warning(details, null);
    }
    
    /**
     * Logs an <code>Status.WARNING</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
	 * test.warning(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest warning(Throwable t, MediaEntityModelProvider provider) {
    	log(Status.WARNING, t, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.WARNING</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest warning(Throwable t) {
        return warning(t, null);
    }
    
    /**
     * Logs an event with <code>Status.WARNING</code> and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest warning(Markup m) {
        log(Status.WARNING, m);
        return this;
    }
    
    /**
     * Logs an <code>Status.ERROR</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest error(String details, MediaEntityModelProvider provider) {
    	log(Status.ERROR, details, provider);
    	return this;
    }
    
    /**
     * Logs an event <code>Status.ERROR</code> with details
     * 
     * @param details Details
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest error(String details) {
        return error(details, null);
    }
    
    /**
     * Logs an <code>Status.ERROR</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
	 * test.error(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider  A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest error(Throwable t, MediaEntityModelProvider provider) {
    	log(Status.ERROR, t, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.ERROR</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest error(Throwable t) {
        return error(t, null);
    }
    
    /**
     * Logs an event with <code>Status.ERROR</code> and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest error(Markup m) {
        log(Status.ERROR, m);
        return this;
    }
    
    /**
     * 
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest skip(String details, MediaEntityModelProvider provider) {
    	log(Status.SKIP, details, provider);
    	return this;
    }
    
    /**
     * Logs an event <code>Status.SKIP</code> with details
     * 
     * @param details Details
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest skip(String details) {
        return skip(details, null);
    }
    
    /**
     * Logs an <code>Status.SKIP</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
	 * test.skip(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest skip(Throwable t, MediaEntityModelProvider provider) {
    	log(Status.SKIP, t, provider);
    	return this;
    }
    
    /**
     * Logs an event with <code>Status.SKIP</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest skip(Throwable t) {
        return skip(t, null);
    }
    
    /**
     * Logs an event with <code>Status.SKIP</code> and custom {@link Markup} such as:
     * 
     * <ul>
     * 	<li>Code block</li>
     * 	<li>Label</li>
     * 	<li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest skip(Markup m) {
        log(Status.SKIP, m);
        return this;
    }
    
    /**
     * Logs an <code>Status.DEBUG</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * @param details Details
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object
     */
    public CustomTest debug(String details, MediaEntityModelProvider provider) {
        log(Status.DEBUG, details, provider);
        return this;
    }
    
    /**
     * Logs an event <code>Status.DEBUG</code> with details
     * 
     * @param details Details
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest debug(String details) {
        return debug(details, null);
    }
    
    /**
     * Logs an <code>Status.DEBUG</code> event with an exception and a media object: 
     * {@link Screencast} or {@link ScreenCapture}
     * 
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * Exception exception = new NullPointerException();
     * test.debug(exception, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
     * </pre>
     * 
     * @param t {@link Throwable}
     * @param provider A {@link MediaEntityModelProvider} object
     * 
     * @return An {@link CustomTest} object 
     */
    public CustomTest debug(Throwable t, MediaEntityModelProvider provider) {
        log(Status.DEBUG, t, provider);
        return this;
    }
    
    /**
     * Logs an event with <code>Status.SKIP</code> and exception
     * 
     * @param t {@link Throwable}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest debug(Throwable t) {
        return debug(t, null);
    }
    
    /**
     * Logs an event with <code>Status.DEBUG</code> and custom {@link Markup} such as:
     * 
     * <ul>
     *  <li>Code block</li>
     *  <li>Label</li>
     *  <li>Table</li>
     * </ul>
     * 
     * @param m {@link Markup}
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest debug(Markup m) {
        log(Status.DEBUG, m);
        return this;
    }    

    /**
     * Assigns a category or group
     * 
     * @param category Category name
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest assignCategory(String... category) {
    	Arrays.stream(category).filter(StringUtil::isNotNullOrEmpty).forEach(c -> {
    		Category objCategory = new Category(c.replace(" ", " "));
	        test.setCategory(objCategory);
	        
	        extent.assignCategory(test, objCategory);
    	});

        return this;
    }

    /**
     * Assigns an author
     * 
     * @param author Author name
     * 
     * @return {@link CustomTest} object
     */
    public CustomTest assignAuthor(String... author) {
    	Arrays.stream(author).filter(StringUtil::isNotNullOrEmpty).forEach(a -> {
    		Author objAuthor = new Author(a.replace(" ", ""));
	        test.setAuthor(objAuthor);
	        
	        extent.assignAuthor(test, objAuthor);
    	});
        
        return this;
    }

    @Override
    public CustomTest addScreenCaptureFromPath(String imagePath, String title) throws IOException {
        ScreenCapture sc = new ScreenCapture();
        sc.setPath(imagePath);
        if (title != null)
            sc.setName(title);
        sc.setMediaType(MediaType.IMG);
        
        test.setScreenCapture(sc);

        if (test.getObjectId() != null) {
            int sequence = test.getScreenCaptureList().size();
            sc.setTestObjectId(test.getObjectId());
            sc.setSequence(sequence);
        }
        
        extent.addScreenCapture(test, sc);
        
        return this;
    }
    
    @Override
    public CustomTest addScreenCaptureFromPath(String imagePath) throws IOException {
        return addScreenCaptureFromPath(imagePath, null);
    }

    @Override
    public CustomTest addScreencastFromPath(String screencastPath) throws IOException {
        Screencast sc = new Screencast();
        sc.setPath(screencastPath);
        sc.setMediaType(MediaType.VID);
        
        test.setScreencast(sc);
        
        if (test.getObjectId() != null) {
            int sequence = test.getScreencastList().size();
            sc.setTestObjectId(test.getObjectId());
            sc.setSequence(sequence);
        }
        
        extent.addScreencast(test, sc);
        
        return this;
    }
    
    /**
     * Provides the current run status of the test or node
     * 
     * @return {@link Status}
     */
    public Status getStatus() {
        return getModel().getStatus();
    }

    /**
     * Returns the underlying test which controls the internal model
     * 
     * @return {@link Test} object
     */
    public Test getModel() {
        return test;
    }
    
    void setUseManualConfiguration(Boolean b) {
        getModel().setUseManualConfiguration(b);
    }
    
}
