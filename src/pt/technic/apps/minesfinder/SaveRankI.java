package pt.technic.apps.minesfinder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SaveRankI {

	FileInputStream fis = null;
	DataInputStream dis = null;

	ArrayList<Integer> EasyL = new ArrayList<Integer>();
	ArrayList<Integer> MediumL = new ArrayList<Integer>();
	ArrayList<Integer> HardL = new ArrayList<Integer>();
	ArrayList<String> EasyN = new ArrayList<String>();
	ArrayList<String> MediumN = new ArrayList<String>();
	ArrayList<String> HardN = new ArrayList<String>();

	public SaveRankI() {
		File f = new File("saveRank.txt");
		String str;
		if (f.canRead()) {
			try {

				FileReader fd = null;
				try {
					fd = new FileReader("saveRank.txt");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BufferedReader reader = new BufferedReader(fd);

				while (!(str = reader.readLine()).equals("--")) {
					StringTokenizer st = new StringTokenizer(str, " ");
					EasyL.add(Integer.parseInt(st.nextToken()));
					EasyN.add(st.nextToken());
				}
				while (!(str = reader.readLine()).equals("--")) {
					StringTokenizer st = new StringTokenizer(str, " ");
					MediumL.add(Integer.parseInt(st.nextToken()));
					MediumN.add(st.nextToken());
				}
				while ((str = reader.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(str, " ");
					HardL.add(Integer.parseInt(st.nextToken()));
					HardN.add(st.nextToken());
				}

				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}