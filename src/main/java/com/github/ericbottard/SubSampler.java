package com.github.ericbottard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

/**
 * A command to read data from stdout and emit sub-sampled data to stdout.
 *
 * @author Eric Bottard
 */
public class SubSampler {

	public static void main(String[] args) throws IOException {

		Options options = new Options();
		try {

			JCommander jCommander = new JCommander(options, args);
		}
		catch (ParameterException pe) {
			System.err.println(pe.getMessage());
			StringBuilder sb = new StringBuilder();
			JCommander jCommander = new JCommander(options);
			jCommander.setProgramName(SubSampler.class.getSimpleName());
			jCommander.usage(sb);
			System.err.println(sb);
			System.exit(-1);
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		if (options.input != null) {
			reader = new BufferedReader(new FileReader(options.input));
		}

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		if (options.output != null) {
			writer = new BufferedWriter(new FileWriter(options.output));
		}

		Map<List<String>, Long> identities = new HashMap<>();

		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(",");
			long sample = Long.parseLong(tokens[options.columnToSample]);
			List<String> identity = new ArrayList<String>();
			for (int i : options.identityColumns) {
				identity.add(tokens[i]);
			}

			long last = identities.getOrDefault(identity, sample - options.interval - 1);
			if (sample >= last + options.interval) {
				identities.put(identity, sample);
				writer.append(line).append('\n');
			}
		}

		reader.close();
		writer.close();
	}
}
