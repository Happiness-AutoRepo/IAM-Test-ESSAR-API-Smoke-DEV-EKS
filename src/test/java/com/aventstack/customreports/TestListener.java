package com.aventstack.customreports;

import java.io.IOException;

import com.aventstack.customreports.model.Author;
import com.aventstack.customreports.model.Category;
import com.aventstack.customreports.model.Log;
import com.aventstack.customreports.model.ScreenCapture;
import com.aventstack.customreports.model.Screencast;
import com.aventstack.customreports.model.Test;

/**
 * Listener methods invoked when an event occurs in the report:
 * 
 * <ul>
 *  <li>A test or node is started</li>
 *  <li>A category or author is added</li>
 *  <li>A media object is added etc.</li>
 * </ul>
 */
public interface TestListener {
    
    /**
     * Invoked when a test is started using <code>createTest(args)</code>
     * 
     * @param test {@link com.aventstack.customreports.model.Test} object
     */
    void onTestStarted(Test test);
    
    /**
     * Invoked when a node is started using <code>createNode(args)</code>
     * 
     * @param node {@link com.aventstack.customreports.model.Test} object
     */
    void onNodeStarted(Test node);
    
    /**
     * Invoked each time a log is added to any test/node
     * 
     * @param test {@link com.aventstack.customreports.model.Test} object
     * @param log {@link com.aventstack.customreports.model.Log} object
     */
    void onLogAdded(Test test, Log log);
    
    /**
     * Invoked each time a category is assigned to any test/node
     * 
     * @param test {@link com.aventstack.customreports.model.Test} object
     * @param category {@link com.aventstack.customreports.model.Category} object
     */
    void onCategoryAssigned(Test test, Category category);
    
    /**
     * Invoked each time an author is assigned to any test/node
     * 
     * @param test {@link com.aventstack.customreports.model.Test} object
     * @param author {@link com.aventstack.customreports.model.Author}
     */
    void onAuthorAssigned(Test test, Author author);
    
    /**
     * Invoked each time a screencapture is added to test
     * 
     * @param test {@link com.aventstack.customreports.model.Test} object
     * @param screenCapture {@link com.aventstack.customreports.model.ScreenCapture} object
     * @throws IOException Exception thrown if the media object is not found
     */
    void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException;
    
    /**
     * Invoked each time a screencapture is added to log
     * 
     * @param log {@link com.aventstack.customreports.model.Log} object
     * @param screenCapture {@link com.aventstack.customreports.model.ScreenCapture} object
     * @throws IOException Exception thrown if the media object is not found
     */
    void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException;
    
    /**
     * Invoked each time a screencast is added
     * 
     * @param test {@link com.aventstack.customreports.model.Test} object
     * @param screencast {@link com.aventstack.customreports.model.Screencast} object
     * @throws IOException Exception thrown if the media object is not found
     */
    void onScreencastAdded(Test test, Screencast screencast) throws IOException;
    
}
