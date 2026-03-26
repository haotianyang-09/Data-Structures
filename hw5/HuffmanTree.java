public class HuffmanTree {
    HuffmanNode root;
    public HuffmanTree(HuffmanNode huff){
        this.root=huff;
    }

    public void printLegend(){
        printLegend(root, "");
    }

    private void printLegend(HuffmanNode node, String code){
        if(node==null){
            return;
        }
        if(node.letter == null){
            return;
        }
        if(node.letter.length()>1) {
            printLegend(node.left, code + "0");
            printLegend(node.right, code + "1");
        }
        if(node.letter.length()==1){
            System.out.println(node.letter+"="+code);
        }
    }

    public static BinaryHeap legendToHeap(String legend){
        BinaryHeap heap=new BinaryHeap<>();
        String [] elements=legend.split(" ");
        for(int i=0;i<elements.length;i+=2){
            String letter=elements[i];
            Double frequency=Double.parseDouble(elements[i+1]);

            HuffmanNode hnode=new HuffmanNode(letter,frequency);
            heap.insert(hnode);
        }

        return heap;
    }

    public static HuffmanTree createFromHeap(BinaryHeap b){
        while (b.getSize() != 1) {
            HuffmanNode node1 = (HuffmanNode)b.deleteMin();
            HuffmanNode node2 = (HuffmanNode)b.deleteMin();

            HuffmanNode merged = new HuffmanNode(node1, node2);
            merged.left=node1;
            merged.right=node2;
            b.insert(merged);
        }
        HuffmanTree tree = new HuffmanTree((HuffmanNode)b.findMin());
        return tree;
    }

    public static void main(String[] args){
        String legend= "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
        BinaryHeap heap=HuffmanTree.legendToHeap(legend);
        heap.printHeap();
        HuffmanTree node=HuffmanTree.createFromHeap(heap);
        node.printLegend();
    }
}

