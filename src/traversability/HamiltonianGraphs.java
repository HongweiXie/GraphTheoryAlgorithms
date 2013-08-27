package traversability;

/**
 * 包含哈密顿回路的图就成为哈密顿图，目前还没有有效的办法判断一个图是否为哈密顿图的充要条件。介绍几个重要定理
 * 1.Let G be a graph of order>=3. If degu+degv>=n for each pair u,v of nonadjacent vertices of G, then G is Hamiltonian.
 * 2.A graph is Hamiltonian if and only if its closure is Hamiltonian. The closure C(G) of a graph G of order n is the graph obtained from
 * G by recursively joining pairs of nonadjacent vertices whose degree sum is at least n until no such pair remains.
 * 3.Let G be a graph of order n>=3. If for every integer j with 1<=j<n/2, the number of vertices of G with degree at most j is less than j,
 * then G is Hamiltonian.
 * 4.If G is a Hamiltonian graph, then for every nonempty proper set S of vertices of G, k(G-S)<=|S|. k(G) denote the number of components in
 * a graph G.
 * @author xhw
 *
 */
public class HamiltonianGraphs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
