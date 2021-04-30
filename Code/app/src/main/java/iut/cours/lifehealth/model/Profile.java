package iut.cours.lifehealth.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import iut.cours.lifehealth.model.utils.DateUtil;

/**
 * A class for a profile of user
 */
public class Profile implements Serializable {
    /**
     * The kind of the user
     */
    private Kind kind;
    /**
     * The height of the user
     */
    private Height height;
    /**
     * The weight of the user
     */
    private Weight weight;
    /**
     * The hydratation of the user
     */
    private Water hydratation;
    /**
     * The steps of the user
     */
    private Step steps;
    /**
     * The birthday of the user
     */
    private Date birthday;

    private DataTime sleep;

    /**
     * A constructor of the class Profile
     * @param kind The kind of the user
     * @param height The height of the user
     * @param weight The weight of the user
     * @param hydratation The hydratation of the user
     * @param steps The steps of the user
     * @param birthday The birthday of the user
     */
    public Profile(Kind kind, Height height, Weight weight, Water hydratation, Step steps, Date birthday, DataTime sleep) {
        this.kind = kind;
        this.height = height;
        this.weight = weight;
        this.hydratation = hydratation;
        this.steps = steps;
        this.birthday = birthday;
        this.sleep = sleep;
    }

    /**
     * A constructor of the class Profile
     */
    public Profile() {
        this(Kind.MAN, new Height(), new Weight(), new Water(), new Step(), new Date(new Date().getTime()), new DataTime());
    }

    /**
     * This method remove all data of more than one year
     */
    public void annualUpdate() {
        this.height.annualUpdate();
        this.weight.annualUpdate();
        this.hydratation.annualUpdate();
        this.steps.annualUpdate();
        this.sleep.annualUpdate();
    }

    /**
     * The setter of the kind of the user
     * @param kind The kind of the user
     */
    public void setKind(Kind kind) {
        this.kind = kind;
    }

    /**
     * The setter of the height of the user
     * @param height The height of the user
     */
    private void setHeight(Height height) {
        this.height = height;
    }

    /**
     * The setter the weight of the user
     * @param weight The weight of the user
     */
    private void setWeight(Weight weight) {
        this.weight = weight;
    }

    /**
     * The setter of the hydratation of the user
     * @param hydratation The hydratation of the user
     */
    private void setHydratation(Water hydratation) {
        this.hydratation = hydratation;
    }

    /**
     * The setter of the birthday of the user
     * @param birthday The birthday of the user
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * The setter of the steps of the user
     * @param steps The steps of the user
     */
    private void setSteps(Step steps) {
        this.steps = steps;
    }

    /**
     * The setter of the sleep time of the user
     * @param sleep  the sleep time of the user
     */
    public void setSleep(DataTime sleep) {
        this.sleep = sleep;
    }

    /**
     * The getter of the kind of the user
     * @return The kind of the user
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * The getter of the height of the user
     * @return The height of the user
     */
    public Height getHeight() {
        return height;
    }

    /**
     * The getter of the weight of the user
     * @return The weight of the user
     */
    public Weight getWeight() {
        return weight;
    }

    /**
     * The getter of the hydratation of the user
     * @return The hydratation of the user
     */
    public Water getHydratation() {
        return hydratation;
    }

    /**
     * The getter of the birthday of the user
     * @return The birthday of the user
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * The getter of the age of the user
     * @return The age of the user
     */
    public int getAge() {
        return new DateUtil().getAge(getBirthday());
    }

    /**
     * The getter of the steps of the user
     * @return The steps of the user
     */
    public Step getSteps() {
        return steps;
    }

    /**
     *The getter of the sleep time of the user
     * @return The sleep time of the user
     */
    public DataTime getSleep() {
        return sleep;
    }


}
