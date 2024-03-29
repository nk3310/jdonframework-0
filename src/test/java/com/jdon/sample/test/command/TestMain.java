/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.jdon.sample.test.command;

import junit.framework.Assert;

import com.jdon.controller.AppUtil;

public class TestMain {

	public static void main(String[] args) {
		AppUtil appUtil = new AppUtil();
		AComponentIF a = (AComponentIF) appUtil.getComponentInstance("producerforCommand");
		BModel bModel = new BModel("model-b");
		TestCommand testCommand = a.send(bModel);
		int i = 0;
		long start = System.currentTimeMillis();
		while (testCommand.getOutput() != 1999) {
			i++;
		}
		long stop = System.currentTimeMillis();
		Assert.assertEquals(testCommand.getOutput(), 1999);
		System.out.print(", time = " + (stop - start) + ", i = " + i);
	}
}
