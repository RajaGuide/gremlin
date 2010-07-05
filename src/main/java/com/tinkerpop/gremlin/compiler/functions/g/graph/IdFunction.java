package com.tinkerpop.gremlin.compiler.functions.g.graph;

import java.util.List;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.functions.Function;
import com.tinkerpop.gremlin.compiler.functions.helpers.GraphFunctionHelper;
import com.tinkerpop.gremlin.compiler.operations.Operation;

/**
 * @author Pavel A. Yaskevich
 */
public class IdFunction implements Function {

	private final String FUNCTION_NAME = "id";
	
	public Atom compute(List<Operation> params) throws RuntimeException {
		if (params.size() == 0 || params.size() > 2)
			throw new RuntimeException("Unsupported arguments for g:load().");
		
		Graph  graph  	 = null;
		Object identifer = null;
		
		// graph variable as first param
		if (params.size() == 2) {
			graph = GraphFunctionHelper.getGraph(params.get(0));
			identifer = params.get(1).compute();
		} else {
			// only identifier in params
			graph = GraphFunctionHelper.getGraph(null);
			identifer = params.get(0).compute();
		}

        return new Atom(graph.getVertex(identifer));
	}

	public String getFunctionName() {
		return this.FUNCTION_NAME;
	}

}
