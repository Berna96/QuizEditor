import model.McModel;
import model.TfModel;
import view.View;

import java.io.IOException;

import controller.Controller;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View v = new View();
		McModel model_mc;
		TfModel model_tf;
		String[] files = v.getFileNames();
		try {
			model_mc = new McModel(files[0]);
			model_tf = new TfModel();
			Controller c = new Controller(model_mc, model_tf, v);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
