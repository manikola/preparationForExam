package exam02.photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {

    private List<Photo> photos = new ArrayList<>();

    public List<Photo> getPhotos() {
        return new ArrayList<>(photos);
    }

    public void addPhoto(String...pictures){
        for(String item : pictures){
            photos.add(new Photo(item));
        }
    }

    public void starPhoto(String str, Quality q){
        boolean found = false;
        for(Photo item : photos){
            if(item.getName().equals(str)){
                item.setQuality(q);
                found = true;
            }
        }
        if(!found){
            throw new PhotoNotFoundException("No photo");
        }

    }
    public int numberOfStars(){
        int sum = 0;
        for(Photo item : photos){
            sum += item.getQuality().getStars();
        }
        return sum;
    }

}
