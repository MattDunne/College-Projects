/*********************************************
*        Student Name: Matthew Dunne         *
*      Student Number: 20087584              *
*              Group : 5N/50                 *
*          Instructor: Kashif Amjad          *
*              Class : CP 2530               *
*********************************************/


class AVLNode
{
    protected int data;
    protected int height;
    private AVLNode leftLink;
    private AVLNode rightLink;
    private AVLNode parent;

    public AVLNode()
    {
        data = 0;
        height = -1;
        leftLink = rightLink = parent = null;
    }

    public void setData(int data)
    {
        this.data = data;
    }

    public int getData()
    {
        return data;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setLeftLink(AVLNode leftLink)
    {
        this.leftLink = leftLink;
    }

    public AVLNode getLeftLink()
    {
        return leftLink;
    }

    public void setRightLink(AVLNode rightLink)
    {
        this.rightLink = rightLink;
    }

    public AVLNode getRightLink()
    {
        return rightLink;
    }

    public void setParent(AVLNode parent)
    {
        this.parent = parent;
    }

    public AVLNode getParent()
    {
        if (parent == null)
        {
            return null;
        } else
        {
            return parent;
        }
    }
}

class AVLTree
{

    protected AVLNode root;

    public AVLTree()
    {
        root = null;
    }

    public void insert(int data)
    {
        boolean duplicate = false;
        
        AVLNode x = new AVLNode();
        x.setData(data);
 
        if (root == null)
        {
            root = x;
        } else
        {
            AVLNode temp = root;
            while (temp != null)
            {

                if (data < temp.getData())
                {
                    if (temp.getLeftLink() != null)
                    {
                        temp = temp.getLeftLink();
                    } else
                    {
                        temp.setLeftLink(x);
                        x.setParent(temp);
                        break;
                    }
                } else if (data > temp.getData())
                {
                    if (temp.getRightLink() != null)
                    {
                        temp = temp.getRightLink();
                    } else
                    {
                        temp.setRightLink(x);
                        x.setParent(temp);
                        break;
                    }
                } 
                else
                {
                    System.out.println("Attempting to insert a duplicate; cannot insert: " + data);
                    duplicate = true;
                    break;
                }
            }
            
            if(!duplicate)
            {
                System.out.println("Inserting:" + data);
            }
        }

        if (getHeight(root) > 1)
        {
            AVLNode y = new AVLNode();
            AVLNode z = new AVLNode();
            if (x.getParent() != null)
            {
                y = x.getParent();
            }
            if (x.getParent() != null && y.getParent() != null)
            {
                z = y.getParent();
            }

            if (getHeight(z.getLeftLink()) - getHeight(z.getRightLink()) == 2)
            {
                if (y.getRightLink() == x)
                {
                    rl(x, y, z);
                } else
                {
                    r(x, y, z);
                }

            } else if (getHeight(z.getLeftLink()) - getHeight(z.getRightLink()) == -2)
            {
                if (y.getRightLink() == x)
                {
                    l(x, y, z);
                } else
                {
                    lr(x, y, z);
                }

            }
        }

    }

    public AVLNode search(int n)
    {

        AVLNode temp = root;
        
        
        
        while (temp != null)
        {
            if (n == temp.getData())
            {
                break;
            }
            if (n < temp.getData())
            {
                temp = temp.getLeftLink();
                
                if(temp == null)
                {
                    break;
                }
            }
            if (n > temp.getData())
            {
                temp = temp.getRightLink();
                
                if(temp == null)
                {
                    break;
                }
            }
        }
        
        
        return temp;
    }

    public void remove(int s)
    {
        AVLNode x = search(s);
        boolean isNull = false;
        
        if (x == null)
        {
            System.out.println("Attempting to remove a number not present in the AVL Tree; Cannot remove: " + s);
            isNull = true;
        }
        else
        {
            System.out.println("Removing: " + s);
        }

        if (!isNull)
        {
            if (x.getRightLink() == null && x.getLeftLink() == null)
            {
                if (x == x.getParent().getRightLink())
                {
                    x.getParent().setRightLink(null);
                } else
                {
                    x.getParent().setLeftLink(null);
                }
            }

            if (x.getRightLink() != null && x.getLeftLink() == null)
            {
                if (x == x.getParent().getRightLink())
                {
                    x.getParent().setRightLink(x.getRightLink());
                    x.setRightLink(null);
                } else
                {
                    x.getParent().setLeftLink(x.getLeftLink());
                    x.setRightLink(null);
                }
            }

            if (x.getLeftLink() != null && x.getRightLink() == null)
            {
                if (x == x.getParent().getLeftLink())
                {
                    x.getParent().setLeftLink(x.getLeftLink());
                    x.setLeftLink(null);
                } else
                {
                    x.getParent().setRightLink(x.getRightLink());
                    x.setLeftLink(null);
                }
            }

            if (x.getLeftLink() != null && x.getRightLink() != null)
            {
                AVLNode temp = x.getRightLink();
                if (temp.getLeftLink() == null)
                {
                    temp.setLeftLink(x.getLeftLink());
                    temp.setParent(x.getParent());
                    x.setRightLink(null);
                    if (x.getParent() !=  null && x == x.getParent().getLeftLink())
                    {
                        x.getParent().setLeftLink(temp);
                    } 
                    else if(x.getParent() != null && x == x.getParent().getRightLink())
                    {
                        x.getParent().setRightLink(temp);
                    }
                } else if (x != root)
                {
                    while (temp.getLeftLink() != null)
                    {
                        temp = temp.getLeftLink();
                    }
                    temp.setLeftLink(x.getLeftLink());
                    temp.setRightLink(x.getRightLink());
                    temp.setParent(x.getParent());
                    if (x == x.getParent().getLeftLink())
                    {
                        x.getParent().setLeftLink(temp);
                    } else
                    {
                        x.getParent().setRightLink(temp);
                    }
                }
                if (x == root)
                {
                    while (temp.getLeftLink() != null)
                    {
                        temp = temp.getLeftLink();
                    }
                    x.setData(temp.getData());
                    temp.getParent().setLeftLink(null);
                    temp.setParent(null);

                }
            }
            if (getHeight(x) > 1)
            {
                AVLNode y = new AVLNode();
                AVLNode z = new AVLNode();
                if (x.getParent() != null)
                {
                    y = x.getParent();
                }
                if (x.getParent() != null && y.getParent() != null)
                {
                    z = y.getParent();
                }

                if (getHeight(z.getLeftLink()) - getHeight(z.getRightLink()) == 2)
                {
                    if (y.getRightLink() == x)
                    {
                        rl(x, y, z);
                    } else
                    {
                        r(x, y, z);
                    }

                } 
                else if (getHeight(z.getLeftLink()) - getHeight(z.getRightLink()) == -2)
                {
                    if (y.getRightLink() == x)
                    {
                        l(x, y, z);
                    } 
                    else
                    {
                        lr(x, y, z);
                    }

                }

            }
        }
        
        

    }

    public int getHeight(AVLNode b)
    {
        if (b == null)
        {
            return -1;
        } 
        else
        {
            return 1 + Math.max(getHeight(b.getLeftLink()), getHeight(b.getRightLink()));
        }
    }

    public void l(AVLNode x, AVLNode y, AVLNode z)
    {

        AVLNode t0, t1, t2, t3;
        t0 = z.getLeftLink();
        t1 = y.getLeftLink();
        t2 = x.getLeftLink();
        t3 = x.getRightLink();
        y.setLeftLink(z);
        y.setRightLink(x);
        y.setParent(z.getParent());
        if (z.getParent() != null)
        {
            z.getParent().setRightLink(y);
        }
        z.setLeftLink(t0);
        z.setRightLink(t1);
        z.setParent(y);
        x.setLeftLink(t2);
        x.setRightLink(t3);
        x.setParent(y);
        if (z == root)
        {
            root = y;
        }

    }

    public void r(AVLNode x, AVLNode y, AVLNode z)
    {
        AVLNode t0, t1, t2, t3;
        t0 = x.getLeftLink();
        t1 = x.getRightLink();
        t2 = y.getRightLink();
        t3 = z.getRightLink();
        y.setLeftLink(x);
        y.setRightLink(z);
        y.setParent(z.getParent());
       
        if (z.getParent() != null)
        {
            z.getParent().setLeftLink(y);
        }
       
        x.setLeftLink(t0);
        x.setRightLink(t1);
        x.setParent(y);
        z.setLeftLink(t2);
        z.setRightLink(t3);
        z.setParent(y);
        
        if (z == root)
        {
            root = y;
        }
    }

    public void lr(AVLNode x, AVLNode y, AVLNode z)
    {
        AVLNode t0, t1, t2, t3;
        t0 = z.getLeftLink();
        t1 = x.getLeftLink();
        t2 = x.getRightLink();
        t3 = y.getRightLink();
        x.setLeftLink(z);
        x.setRightLink(y);
        x.setParent(z.getParent());
       
        if (z.getParent() != null)
        {
            z.getParent().setRightLink(x);
        }
        
        z.setLeftLink(t0);
        z.setRightLink(t1);
        z.setParent(x);
        y.setLeftLink(t2);
        y.setRightLink(t3);
        y.setParent(x);
        
        if (z == root)
        {
            root = x;
        }
    }

    public void rl(AVLNode x, AVLNode y, AVLNode z)
    {
        AVLNode t0, t1, t2, t3;
        t0 = y.getLeftLink();
        t1 = x.getLeftLink();
        t2 = x.getRightLink();
        t3 = z.getRightLink();
        x.setLeftLink(y);
        x.setRightLink(z);
        x.setParent(z.getParent());
        
        if (z.getParent() != null)
        {
            z.getParent().setLeftLink(x);
        }
        
        y.setLeftLink(t0);
        y.setRightLink(t1);
        y.setParent(x);
        z.setLeftLink(t2);
        z.setRightLink(t3);
        z.setParent(x);
        
        if (z == root)
        {
            root = x;
        }
    }

    public void showPreorder()
    {
        System.out.print("Preorder traversal: ");
        preorder(root);
        System.out.println();
    }

    public void preorder(AVLNode a)
    {
        if (a != null)
        {
            System.out.print(a.getData() + " ");
            preorder(a.getLeftLink());
            preorder(a.getRightLink());
        }
    }

    public void showInorder()
    {
        System.out.print("Inorder traversal: ");
        inorder(root);
        System.out.println();
    }

    public void inorder(AVLNode a)
    {
        if (a != null)
        {
            inorder(a.getLeftLink());
            System.out.print(a.getData() + " ");
            inorder(a.getRightLink());
        }
    }

    public void showPostorder()
    {
        System.out.print("Postorder traversal: ");
        postorder(root);
        System.out.println();
    }

    public void postorder(AVLNode a)
    {
        if (a != null)
        {
            postorder(a.getLeftLink());
            postorder(a.getRightLink());
            System.out.print(a.getData() + " ");
        }
    }
}

class Lab51
{

    public static void main(String[] args)
    {
        AVLTree a = new AVLTree();
        
        a.insert(22);
        a.insert(9);
        a.insert(8);
        a.insert(41);
        a.insert(6);
        a.insert(7);
        a.insert(56);
        a.insert(12);
        a.insert(1);
        a.insert(51);
        System.out.println("");
        
        a.showPreorder();
        a.showInorder();
        a.showPostorder();
        System.out.println("Height: " + a.getHeight(a.root));
        System.out.println("");
        
        a.remove(6);
        a.remove(9);
        a.remove(6);
        a.remove(58);
        a.insert(1);
        a.insert(56);
        a.insert(3);
        a.insert(17);
        System.out.println("");
        
        a.showPreorder();
        a.showInorder();
        a.showPostorder();
        System.out.println("Height: " + a.getHeight(a.root));

    }
}