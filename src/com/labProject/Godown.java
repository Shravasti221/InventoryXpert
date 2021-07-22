package com.labProject;

import java.util.ArrayList;

class GodownError extends RuntimeException {
    float space_left;
    int no_of_items = -1;
    ItemBasic obj;

    GodownError(ItemBasic item, float space_left_) {
        this.space_left = space_left_;
    }

    public String toString() {
        return "The godown doesn't have space for that many items, space left is " + space_left + "";
    }
}
public class Godown {
    int space_left;
    public ArrayList<ItemBasic> i = null;
    public ArrayList<Consumer> c = null;
    public ArrayList<Producer> p = null;

    Godown(){
        String[] NamesConsumer = {"Nita", "Ishaan", "Dhruv",  "Shyla", "Amar", "Diya", "Ananya", "Agastya", "Jaya", "Anjali","Nikhil","Sahil","Ishani","Ambar","Darsh","Divya","Ashwin","Deven","Shaila","Shylah","Avany","Artha","Farid","Salina","Charu","Devi","Amitabh", "Lata","Arun","Dhara","Akhilesh", "Arti","Akshay","Bharat","Damayanti","Chander","Salena","Tanaia","Shalene","Shalena","Anand","Shashi","Anusha","Shaleena"};
        String[] Nums = {"9499694505", "9325388957", "9763765185", "9125529525", "9602188949", "9472796414", "9769116584", "9683525623", "9734651717", "9749904136", "9445947172", "9584669108", "9584624066", "9547468861", "9230249439", "9569166219", "9721077437", "9530594659", "9287309613", "9800466058", "9504531844", "9799816588", "9503970289", "9423998666", "9192960646", "9652535599", "9546692028", "9617448045", "9515412117", "9131876074", "9237631023", "9899818981", "9643783112", "9683424343", "9870458046", "9862730744", "9383101227", "9436850572", "9597422446", "9204980797", "9286981822", "9820646940", "9453476790", "9756311900", "9263942984", "9612718486", "9833356832", "9777980817", "9601451998", "9445461558"};
        for(int k =0, j= 0; k< 20; k++){
            c.add(new Consumer("CONS"+ k, NamesConsumer[j++]), Nums[j++], "CONS"+ k));
        }
    }
    synchronized public void checkSpace( ItemBasic item){
        try {
            if (space_left < item.getSpace()) {
                throw new GodownError(item, space_left);
            }
        }catch (GodownError e){
            System.out.println(e);

        }
    }
    synchronized public void addItem(ItemBasic item){
        i.add(item);
    }
    synchronized public void addConsumer(Consumer cons){
        c.add(cons);
    }
    synchronized public void addProducer(Producer prod) {
        p.add(prod);
    }
}
