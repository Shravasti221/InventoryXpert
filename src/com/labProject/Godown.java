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
    int type;
    ItemError(String ID_, int type){
        this.ID = ID_;
    }
    @Override
    public String toString() {
        switch(type) {
            case 1:
                return "ItemError{ Insufficient ID:" + ID + " wrt placed order}";
            case 2:
                return "ItemError{"+ "Error Adding item: " + ID+"to the godown}";
            default:
                return "ItemError{" + "item ID:" +ID + "}";
        }
    }
}
public class Godown{
    int space_left = 10000;
    public ArrayList<ItemBasic> i;
    private ArrayList<Consumer> c; // consumers that have registered at the godown
    private ArrayList<Producer> p;  // producers that have registered at the godown
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
        String unitNames[] = {"large", "medium", "small", "Kg", "g", "beverage 100ml", "beverage 250ml", "beverage 500ml", "beverage 1l", "beverage 2l", "beverage 5l", "sachets", "packets small", "packets medium", "packets large"};
        Random rand = new Random();
        ItemBasic tempItem = new ItemBasic();
        Producer tempProd = new Producer();
        for(int k =0, j= 0; k< 20 ; k++){
            try {
                this.c.add(new Consumer(("CONS" + k), NamesConsumer[j++], Nums[j++], ("CONS" + k)));
                tempProd = new Producer(("PROD" + k), NamesConsumer[j++], Nums[j++], ("PROD" + k));
                for(int item_iter = 0; item_iter<rand.nextInt(12); item_iter++){
                    System.out.println("line 1");
                    tempItem = new ItemBasic(("ItemName"+rand.nextInt(200)), "PROD"+j, unitNames[rand.nextInt(15)], rand.nextInt(50), rand.nextFloat()*10f, rand.nextInt(300) );
                    System.out.println("line 2");
                    tempProd.itemsProduced.add(tempItem);
                    System.out.println("line 3");
                    this.addItem(tempItem);
                    System.out.println("line 4");
                }
                this.p.add(tempProd);
            }catch(GodownError space_){
                System.out.println(space_ + " All items could not be added.");
                break;
            }
            catch(Exception e){
                System.out.println("All names added " + e);
                //System.out.println(tempItem);
                //System.out.println(tempProd);
                print_vals();
                break;
            }
        }
        print_vals();
    }
    synchronized public void checkSpace( ItemBasic item){
        try {
            if (space_left < item.getSpace()) {
                throw new GodownError(space_left);
            }
        }catch (GodownError e){
            System.out.println(e);
        }
    }

    synchronized public void buyItem(ItemBasic consumer_item) throws ItemError{
        int pos = -1;
        String searchID = consumer_item.getID();
        int qty = consumer_item.getQty();
        ItemBasic item_revised;
        for(int j= 0; j<=i.size(); j++)
            if (searchID.equals(i.get(j).getID()))
                if (i.get(j).getQty() >= qty){
                    qty = i.get(j).getQty() - qty;
                    pos = j;
                    break;
            }
        if(pos != -1) {
            int producerIndex = index.get(i.get(pos).getProducerID());
            Producer p_ = p.remove(producerIndex);
            item_revised = i.remove(pos);
            p_.editRevenueEarned(consumer_item.getAmount());
            item_revised.setQty(qty);
            if(qty !=0) {
                i.add(item_revised);
            }
            p.add(producerIndex, p_);
        }
        else
            throw new ItemError(consumer_item.getID() + ": "+ consumer_item.getName(), 0);
    }

    synchronized public void addItem(ItemBasic item){
        if (item.getID().isEmpty()){
            item.setID("IT"+itemIDCount++);
        }
        try {
            checkSpace(item);
            int producerno = Integer.parseInt(item.getProducerID().replace("PROD", ""));
            Producer producer_removed = p.remove(producerno);
            producer_removed.addItem(item);
            p.add(producerno, producer_removed);
        }catch (NumberFormatException e ){
            System.out.println("Could not extract producer number from Prod:" + item.getProducerID());
            throw new ItemError(item.getID(), 1);
        }
        i.add(item);
    }
    synchronized public void addConsumer(Consumer cons){
        c.add(cons);
    }
    synchronized public void addProducer(Producer prod) {
        p.add(prod);
    }
    synchronized public void updateProducer(Producer prod) {
        int producerNo = Integer.parseInt(prod.getID().replace("PROD", ""));
        p.remove(producerNo);
        p.add(producerNo, prod);

    }
    synchronized public String getConsumerID(){return "CONS"+c.size();}
    synchronized public String getProducerID(){return "PROD"+p.size();}
    synchronized public String getItemID(){return "PROD"+(++itemIDCount);}

    public Consumer getConsumer(String ID){return c.get(index.get(ID));}
    public Producer getProducer(String ID){return p.get(index.get(ID));}

    boolean checkConsumerPassword(String ID, String pwd){return c.get(index.get(ID)).checkPassword(pwd);}
    boolean checkProducerPassword(String ID, String pwd){return c.get(index.get(ID)).checkPassword(pwd);}

    void print_vals(){
        for(Consumer c_: this.c)
            System.out.println(c_);
        for(Producer c_: this.p)
            System.out.println(c_);
        for(ItemBasic c_: this.i)
            System.out.println(c_);
    }

}
