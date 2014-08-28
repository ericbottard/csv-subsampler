/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.ericbottard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;

public class Options {

	@Parameter(names = "--sample", description = "the index of the column to sample (0 based)", validateWith = PositiveInteger.class)
	int columnToSample = 0;

	@Parameter(names = "--interval", description = "the sample interval", required = true, validateWith = PositiveInteger.class)
	int interval = 0;

	@Parameter(names = "--identity", variableArity = true, description = "the index (or indices) of the colums that uniquely identify a line")
	List<Integer> identityColumns = new ArrayList<>();

	@Parameter(names = "--input", description = "file to read as input (default to stdin)")
	File input;

	@Parameter(names = "--output", description = "file to write as output (default to stdout)")
	File output;

}
