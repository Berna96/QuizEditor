import model.McModel;
import model.TfModel;
import view.View;
import controller.Controller;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		View v = new View();
		McModel model_mc = new McModel(null);
		TfModel model_tf = new TfModel();
		Controller c = new Controller(model_mc, model_tf, v);
	}

}
