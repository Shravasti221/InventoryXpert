package com.labProject;

import java.util.ArrayList;
import java.util.Random;
interface IDextractor{
    int get(String s);
}


class GodownError extends RuntimeException {
    float space_left;
    GodownError(float space_left_) {
        this.space_left = space_left_;
    }

    public String toString() {
        return "The godown doesn't have space for that many items, space left is " + space_left + "";
    }
}

class ItemError extends RuntimeException{
    String ID;
    ItemError(String ID_){
        this.ID = ID_;
    }
    @Override
    public String toString() {
                return "ItemError{ Insufficient quantity available for the product ID:" + ID + " wrt placed order}";
        }
    }

public class Godown{
    int space_left = 10000;
    public ArrayList<ItemBasic> i;
    public ArrayList<Consumer> c; // consumers that have registered at the godown
    public ArrayList<Producer> p;  // producers that have registered at the godown
    IDextractor index = (s)->{
        s = s.replace("PROD", "");
        s = s.replace("CONS", "");
        return Integer.parseInt(s);};

    private static long itemIDCount = 0;

    Godown(){
        c = new ArrayList<Consumer>();
        p = new ArrayList<Producer>();
        i = new ArrayList<ItemBasic>();
        String NamesConsumer[] = {"Nita", "Ishaan", "Dhruv",  "Shyla", "Amar", "Diya", "Ananya", "Agastya", "Jaya", "Anjali","Nikhil","Sahil","Ishani","Ambar","Darsh","Divya","Ashwin","Deven","Shaila","Shylah","Avany","Artha","Farid","Salina","Charu","Devi","Amitabh", "Lata","Arun","Dhara","Akhilesh", "Arti","Akshay","Bharat","Damayanti","Chander","Salena","Tanaia","Shalene","Shalena","Anand","Shashi","Anusha","Shaleena"};
        String Nums[] = {"9499694505", "9325388957", "9763765185", "9125529525", "9602188949", "9472796414", "9769116584", "9683525623", "9734651717", "9749904136", "9445947172", "9584669108", "9584624066", "9547468861", "9230249439", "9569166219", "9721077437", "9530594659", "9287309613", "9800466058", "9504531844", "9799816588", "9503970289", "9423998666", "9192960646", "9652535599", "9546692028", "9617448045", "9515412117", "9131876074", "9237631023", "9899818981", "9643783112", "9683424343", "9870458046", "9862730744", "9383101227", "9436850572", "9597422446", "9204980797", "9286981822", "9820646940", "9453476790", "9756311900", "9263942984", "9612718486", "9833356832", "9777980817", "9601451998", "9445461558"};
        String unitNames[] = {"large", "medium", "small", "Kg", "g", "100ml", "250ml", "500ml", "1l", "2l", "5l", "sachet"};
        Random rand = new Random();
        ItemBasic tempItem = new ItemBasic();

        for(int k =0, j= 0; k< 20 ; k++){
            try {
                this.addConsumer(new Consumer(("CONS" + k), NamesConsumer[j++], Nums[j++], ("CONS" + k)));
                this.addProducer(new Producer(("PROD" + k), NamesConsumer[j++], Nums[j++], ("PROD" + k)));

            }
            catch(Exception e){
                System.out.println("All names added " + e);
                break;
            }
        }
        for(int k = 0; k<60; k++) {
            try {
                tempItem = new ItemBasic("itemName"+itemIDCount, "PROD"+rand.nextInt(10), unitNames[rand.nextInt(12)], rand.nextInt(50), rand.nextFloat()*100, rand.nextInt(20));
                this.addNewItem(tempItem);
            } catch (GodownError space_) {
                System.out.println(tempItem + " not added.");
            }
        }
        print_vals();

    }
    synchronized public void checkSpace( ItemBasic item){
            if (space_left < item.getSpace()*item.getQty())
                throw new GodownError(space_left);
    }

    synchronized public void buyItem(ItemBasic itemBought) throws ItemError{
        System.out.println("String ID to search: " + itemBought.getID() );//+ " qty: " + itemBought.getQty());
        for(ItemBasic godownItem: i) {
            if (godownItem.getID().equals(itemBought.getID())){
                //debug code
                if(godownItem == itemBought)
                    System.out.println("GodownItem and itemBought are the same");
                else if(godownItem.equals(itemBought))
                    System.out.println("GodownItem and itemBought are equal");


                if (godownItem.getQty() < itemBought.getQty()) {
                    System.out.println("Insufficient Quantity");
                    throw new ItemError(itemBought.getID());
                }
                System.out.println("Entered buy item if if: with godownItem as : " + godownItem);
                godownItem.setQty(godownItem.getQty() - itemBought.getQty());
                System.out.println("Updated godownItem : " + godownItem);
                System.out.println("Original godown Space : " + space_left);
                this.space_left -= godownItem.getSpace()*itemBought.getQty();
                System.out.println("Updated godown Space : " + space_left);
                getProducer(godownItem.getProducerID()).editRevenueEarned(godownItem.getPrice()*itemBought.getQty());

                //debug code
                if(godownItem == itemBought)
                    System.out.println("GodownItem and itemBought are the same");
                else if(godownItem.equals(itemBought))
                    System.out.println("GodownItem and itemBought are equal");

                return;
            }
        }
        System.out.println(itemBought + " not added.");
        throw new ItemError(itemBought.getID());
    }

    ItemBasic getItem(String checkerItemID){
        for(ItemBasic it: this.i) {
            if (it.getID().equalsIgnoreCase(checkerItemID)) {
                ItemBasic ret_val = it.copy();
                return ret_val;
            }
        }
        return null;
    }

    synchronized public void addNewItem(ItemBasic item){
        if (item.getID().isEmpty()){
            item.setID("IT"+itemIDCount++);
        }
        checkSpace(item);
        this.space_left -= item.getSpace() * item.getQty();
        i.add(item);
        System.out.println("Item: " + item +" added to inventory");
    }
    synchronized public void addConsumer(Consumer cons){
        c.add(cons);
    }
    synchronized public void addProducer(Producer prod) { p.add(prod);}
    synchronized public String getConsumerID(){return "CONS"+c.size();}
    synchronized public String getProducerID(){return "PROD"+p.size();}

    public Consumer getConsumer(String ID){return c.get(index.get(ID));}
    public Producer getProducer(String ID){return p.get(index.get(ID));}

    boolean checkConsumerPassword(String ID, String pwd){return c.get(index.get(ID)).checkPassword(pwd);}
    boolean checkProducerPassword(String ID, String pwd){return p.get(index.get(ID)).checkPassword(pwd);}
    void print_vals(){
        System.out.println("Godown Items : Size = "+ i.size() + "\nCurrent godown space is : "+ this.space_left + "\nAnd the items are:\n" + i);
        System.out.println("Godown Consumers : ");
        for(Consumer c_: this.c)
            System.out.println(c_);
        System.out.println("Godown Producers : ");
        for(Producer c_: this.p)
            System.out.println(c_);
    }

}
