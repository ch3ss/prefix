package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {

	public static List<String> getStdInstance() {
		int offsetA = (int) 'A', l = (int) 'Z' - offsetA + 1;

		StringBuilder sb = new StringBuilder(4);
		sb.append("    \n");
		List<String> list = new ArrayList<>(25 * 25 * 25 * 25);
		for (int i = 0; i < l; i++) {
			sb.setCharAt(0, (char) (i + offsetA));
			for (int j = 0; j < l; j++) {
				sb.setCharAt(1, (char) (j + offsetA));
				for (int k = 0; k < l; k++) {
					sb.setCharAt(2, (char) (k + offsetA));
					for (int m = 0; m < l; m++) {
						sb.setCharAt(3, (char) (m + offsetA));
						list.add(sb.toString());
					}
				}
			}
		}
		return list;

	}

	public void writeToDisc(List<String> list, String path) {
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(path));
			Collections.shuffle(list);
			for (String string : list) {
				wr.write(string);
			}
			wr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
