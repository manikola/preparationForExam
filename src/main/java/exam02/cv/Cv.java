package exam02.cv;

import java.util.ArrayList;
import java.util.List;

public class Cv {

    private String name;
    private List<Skill> skills= new ArrayList<>();

    public Cv(String name) {
        this.name = name;
    }

    public Cv(String name, List<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public List<Skill> getSkills() {
        return new ArrayList<>(skills);
    }

    public void addSkills(String...moreSkills){
        for(String item : moreSkills){
            String skillName = item.substring(0,item.length()-4);
            int level = Integer.parseInt(item.substring(item.length()-2, item.length()-1));
            skills.add(new Skill(skillName, level));
        }
    }

    public int findSkillLevelByName(String skillName){
        if (skills.size() == 0) {
            throw new SkillNotFoundException("Not found");
        }
        int level = 0;
        for(Skill item : skills){
            if(item.getName().equals(skillName)){
                 level = item.getLevel();
            }

        }
        return level;
    }
}
