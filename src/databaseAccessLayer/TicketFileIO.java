package databaseAccessLayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketFileIO implements FileIO {

	private BufferedWriter writer;
	private BufferedReader reader;

	// character used to separate fields
	private String separator = "|";

	public TicketFileIO() {
	}

	@Override
	public void store(ArrayList<String> data) {
		// Create a BufferedWriter that writes to the database
		try {
			writer = new BufferedWriter(new FileWriter("./database/TicketFile.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Build the string to write
		String entry = "";
		for (int i = 0; i < data.size()-1; i++) {
			entry += data.get(i) + separator;
		}
		entry += data.get(data.size()-1);

		// Write to the text file
		try {
			writer.append(entry);
			writer.append("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ArrayList<String>> read() {
		// Create a BufferedReader that reads from the database
		try {
			reader = new BufferedReader(new FileReader("./database/TicketFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Create an ArrayList of entries in TicketFile in format: (id, ProjectId, Name, Descr)
		ArrayList<ArrayList<String>> container = new ArrayList<ArrayList<String>>();
		String line;
		try {
			line = reader.readLine();
			while (line != null) {
				List<String> items = Arrays.asList(line.split("\\|"));
				ArrayList<String> output = new ArrayList<>(items);

				// If descr field is empty, add an empty string to represent it
				if (output.size() < 4) {
					output.add("");
				}

				container.add(output);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return container;
	}


	@Override
	public ArrayList<String> readID(String id) {
		// Create a BufferedReader that reads from the database
		try {
			reader = new BufferedReader(new FileReader("./database/TicketFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Create an ArrayList of entries in TicketFile in format: (id, ProjectId, Name, Descr)
		ArrayList<String> output = null;
		String line;
		try {
			line = reader.readLine();
			while (line != null) {
				List<String> items = Arrays.asList(line.split("\\|"));
				output = new ArrayList<>(items);

				// If descr field is empty, add an empty string to represent it
				if (output.size() < 4) {
					output.add("");
				}

				// If the ID (first field) matches the input id, return the entry
				if (output.get(0).equals(id)) {
					reader.close();
					return output;
				}

				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}


	@Override
	public int size() {
		int lines = 0;
		try {
			reader = new BufferedReader(new FileReader("./database/TicketFile.txt"));
			while (reader.readLine() != null) lines++;
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	@Override
	public void deleteEntry(String id) {
		ArrayList<String> dataToDelete = readID(id);
		String stringToDelete = dataToDelete.get(0) + separator + dataToDelete.get(1) + separator + dataToDelete.get(2) + separator + dataToDelete.get(3);
		try {
			File inFile = new File("./database/TicketFile.txt");

			if (!inFile.isFile()) {
				System.out.println("Parameter is not an existing file");
				return;
			}

			//Construct the new file that will later be renamed to the original filename.
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader br = new BufferedReader(new FileReader(inFile));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			//Read from the original file and write to the new
			//unless content matches data to be removed.
			while ((line = br.readLine()) != null) {

				if (!line.trim().equals(stringToDelete)) {

					pw.println(line);
					pw.flush();
				}
			}
			pw.close();
			br.close();

			//Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			//Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");

		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
