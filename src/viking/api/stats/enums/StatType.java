package viking.api.stats.enums;

/**
 * Created by Sphiinx on 1/4/2017.
 */
public enum StatType {

    RANK(0),
    LEVEL(1),
    XP(2);

    private final int SKILL_TYPE;

    StatType(int skill_type) {
        this.SKILL_TYPE = skill_type;
    }

    /**
     * Gets the specified skill type.
     *
     * @return The specified skill type.
     */
    public int getSkillPosition() {
        return SKILL_TYPE;
    }

}
