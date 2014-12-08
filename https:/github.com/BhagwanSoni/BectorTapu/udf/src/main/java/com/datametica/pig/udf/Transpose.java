package com.datametica.pig.udf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

import org.apache.pig.builtin.IsEmpty;

class Transpose {
	public static void main(String[] args) throws IOException {
		File file = new File(
				"Input_File_Path");
		FileReader in = new FileReader(
				"Output_File_Path");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(in);
		BufferedWriter bw = new BufferedWriter(fw);
		String line = null;
		int maxColumnCount = 0;
		int i = 0, j = 0, rowCount = 0, columnCount = 0;
		while ((line = br.readLine()) != null) {
			columnCount = 0;
			for (String word : line.split("\t")) {
				++columnCount;
			}
			if (columnCount > maxColumnCount)
				maxColumnCount = columnCount;
			++rowCount;
		}
		in.close();
		br.close();
		String[][] table = new String[rowCount][maxColumnCount];
		FileReader input;
		try {
			input = new FileReader(
					"Input_File_Path");
			BufferedReader br1 = new BufferedReader(input);

			while ((line = br1.readLine()) != null) {
				for (String word : line.split("\t")) {
					table[i][j] = word;
					++j;
				}
				j = 0;
				++i;
			} 
			input.close();
			br1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
			
		
		for (j = 0; j < columnCount; j++) {
			for (i = 0; i < rowCount; i++) {
				bw.append(line);
			}
			bw.append("\n");
		}
		bw.close();
	}

}
