package minderupt.spectacular.data.model;

/**
 *  public static final int ABSTAIN = -1;
    public static final int UNKNOWN = 0;
    public static final int BDD = 1;
    public static final int FIT = 2;
    public static final int EUC = 3;

 */
public enum ArtifactType {

    // for voting - no comment
    ABSTAIN,

    // unknown type
    UNKNOWN,

    // behavior driven development artifact
    BDD,

    // FIT/Functional Integrated Test artifact
    FIT,

    // Executable Use Case artifact
    EUC,

    // Data-driven for any other artifact
    DATADRIVEN

}
