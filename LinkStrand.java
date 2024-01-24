


public class LinkStrand implements IDnaStrand {
    private class Node {
        String info;
        Node next;
        Node(String x){
            info = x;
        }
        Node(String x, Node node){
            info = x;
            next = node;
        }
    }

    public LinkStrand () {
        this ("");
    }

    public LinkStrand (String s) {
        initialize(s);

    }
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    @Override
    public void initialize(String source) {
        myFirst = new Node (source);
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
        myAppends = 0;
        mySize = source.length();
        myLast = myFirst;
        


    }

    @Override
    public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

    public long size() {
        return mySize;
    }

    @Override
    public IDnaStrand append (String dna) {
        myLast.next = new Node (dna);
        myLast = myLast.next;
        mySize+= dna.length();
        myAppends++;
        return this;

    }
    @Override
    public int getAppendCount () {
        return myAppends;
    }
    @Override
    public String toString () {
        StringBuilderStrand val = new StringBuilderStrand();
        Node help = myFirst;
        while (help != null) {
            val.append (help.info);
            help = help.next;

        }
        
        return val.toString();
    }

    @Override
    public IDnaStrand reverse() {
        Node helper = myFirst;
        LinkStrand ret = new LinkStrand();

        while (helper != null) {
            StringBuilder h = new StringBuilder();
            h.append (helper.info);
            Node temp = new Node (h.reverse().toString());
            ret.mySize+= h.length();
            temp.next = ret.myFirst;
            ret.myFirst = temp;
            helper = helper.next;
        }


        return ret;

    }


    @Override
    public char charAt(int index) {
        if (index < 0 || index >= mySize) {
            throw new IndexOutOfBoundsException();
        }
        if (index <= myIndex) {
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while (index != myIndex) {
            myIndex += 1;
            myLocalIndex += 1;

            if (myCurrent.info.length() <= myLocalIndex && myCurrent.next != null) {
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }

        return myCurrent.info.charAt(myLocalIndex);
    }

}


