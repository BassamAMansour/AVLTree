package eg.edu.alexu.csd.filestructure.avl.Implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import eg.edu.alexu.csd.filestructure.avl.IDictionary;

public class Dictionary implements IDictionary {
	
	private AVLTree avl;
	public Dictionary(){
		avl = new AVLTree();
	}
	
    @Override
    public void load(File file) {
    	BufferedReader br = null;
		FileReader fr = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				avl.insert(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
    }

    @Override
    public boolean insert(String word) {
    	if(exists(word)) return false;
    	avl.insert(word);
        return true;
    }

    @Override
    public boolean exists(String word) {
        return avl.search(word);
    }

    @Override
    public boolean delete(String word) {
        return avl.delete(word);
    }

    @Override
    public int size() {
        return avl.getNumberOfNodes();
    }

    @Override
    public int height() {
        return avl.height();
    }
}
