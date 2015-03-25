/*
 * The MIT License
 *
 * Copyright 2015 Jesse Glick.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.workflow;

import hudson.model.Run;
import java.io.IOException;
import org.jvnet.hudson.test.JenkinsRule;

/**
 * Utilities that could be added to {@link JenkinsRule} in the future but are not yet available in our baseline version.
 */
public class JenkinsRuleExt {

    // TODO use standard version 1.607+

    /**
     * Waits for a build to complete.
     * @return the same build, once done
     */
    public static <R extends Run<?,?>> R waitForCompletion(R r) throws InterruptedException {
        // Could be using com.jayway.awaitility:awaitility but it seems like overkill here.
        while (r.isBuilding()) {
            Thread.sleep(100);
        }
        return r;
    }

    /**
     * Waits for a build log to contain a specified string.
     * @return the same build, once it does
     */
    public static <R extends Run<?,?>> R waitForMessage(String message, R r) throws IOException, InterruptedException {
        while (!JenkinsRule.getLog(r).contains(message)) {
            Thread.sleep(100);
        }
        return r;
    }

    private JenkinsRuleExt() {}

}