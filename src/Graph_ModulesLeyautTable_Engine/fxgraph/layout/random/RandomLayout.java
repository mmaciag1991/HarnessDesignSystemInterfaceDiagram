package Graph_ModulesLeyautTable_Engine.fxgraph.layout.random;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Graph;
import Graph_ModulesLeyautTable_Engine.fxgraph.layout.base.Layout;

import java.util.Random;

public class RandomLayout extends Layout {

    Graph graph;

    Random rnd = new Random();

    public RandomLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {
/*
        List<Cell> cells = graph.getModel().getAllCells();

        for (Cell cell : cells) {

            double x = rnd.nextDouble() * 2000;
            double y = rnd.nextDouble() * 2000;

            cell.relocate(x, y);

        }

        List<Module> modules = graph.getModel().getAllModules();

        for (Module module : modules) {

            double x = rnd.nextDouble() * 500;
            double y = rnd.nextDouble() * 500;

            module.relocate(530, 530);

        }
*/
    }

}