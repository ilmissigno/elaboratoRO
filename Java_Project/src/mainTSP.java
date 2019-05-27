import gurobi.*;
public class mainTSP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 308;
		if(args.length<1) {
			System.out.println("Errore : Specificare il nome del file");
			System.exit(1);
		}
		try {
			GRBEnv env = new GRBEnv();
			GRBModel model = new GRBModel(env);
			GRBVar[][] vars = new GRBVar[n][n];
			GRBVar[] u = new GRBVar[n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					String st = "X_"+Integer.toString(i)+"_"+Integer.toString(j);
					vars[i][j]=model.addVar(0.0, 1.0, 0.0, GRB.BINARY, st);
				}
			}
			
			for(int i=0;i<n;i++) {
				u[i] = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "u");
			}
			
			GRBLinExpr expr;
			
			for(int i=1;i<n-1;i++) {
				expr = new GRBLinExpr();
				expr.addTerms(null,vars[i]);
				String st = "V_"+Integer.toString(i);
				model.addConstr(expr, GRB.EQUAL, 1.0, st);
			}
			for(int j=2;j<n;j++) {
				expr = new GRBLinExpr();
				expr.addTerms(null,vars[j]);
				String st = "V_"+Integer.toString(j);
				model.addConstr(expr, GRB.EQUAL, 1.0, st);
			}
			for(int k=2;k<n-1;k++) {
			for(int i=1;i<n-1;i++) {
					expr = new GRBLinExpr();
					expr.addTerms(null, vars[i]);
					String st = "V_"+Integer.toString(i)+"_"+Integer.toString(k);
					model.addConstr(expr, GRB.LESS_EQUAL, 1.0, st);
			}
			}
			for(int k=2;k<n-1;k++) {
				for(int j=1;j<n-1;j++) {
						expr = new GRBLinExpr();
						expr.addTerms(null, vars[j]);
						String st = "V_"+Integer.toString(k)+"_"+Integer.toString(j);
						model.addConstr(expr, GRB.LESS_EQUAL, 1.0, st);
				}
				}
			for(int i=1;i<n-1;i++) {
				for(int j=2;j<n;j++) {
					expr = new GRBLinExpr();
					expr.addTerm(1.0, vars[i][j]); //Al posto di uno metti tij caricato dal file excel
					String st = "V_"+Integer.toString(i)+"_"+Integer.toString(j);
					model.addConstr(expr, GRB.LESS_EQUAL, 1.0, st); //Al posto di 1 metti Tmax caricato dal file excel
				}
			}
			for(int i=1;i<n-1;i++) {
				for(int j=1;j<n-1;j++) {
					expr = new GRBLinExpr();
					expr.addTerm(1.0,u[i]);
					expr.addTerm(-1.0, u[j]);
					expr.addTerm(n, vars[i][j]);
					expr.addTerm(-1.0, vars[i][j]);
					String st = "V_"+Integer.toString(i)+"_"+Integer.toString(j);
					model.addConstr(expr, GRB.LESS_EQUAL, n-2.0, st); //Al posto di 1 metti Tmax caricato dal file excel
				}
			}
			
			for(int i=2;i<n;i++) {
				expr = new GRBLinExpr();
				expr.addTerm(1.0, u[i]);
				String st = "V_"+Integer.toString(i);
				model.addConstr(expr, GRB.LESS_EQUAL, n, st);
			}
			
			for(int i=2;i<n;i++) {
				expr = new GRBLinExpr();
				expr.addTerm(1.0, u[i]);
				String st = "V_"+Integer.toString(i);
				model.addConstr(expr, GRB.GREATER_EQUAL, 2, st);
			}
			
			for(int i=2;i<n;i++) {
				for(int j=2;j<n-1;j++) {
					expr = new GRBLinExpr();
					expr.addTerm(1.0, vars[i][j]); //Al posto di uno metti S_i caricato dal file excel
					model.setObjective(expr,GRB.MAXIMIZE);
				}
			}
			
			model.optimize();
			
		}catch(GRBException e) {
			System.out.println("Error code : "+e.getErrorCode()+". "+e.getMessage());
		}
	}

}
