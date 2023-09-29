import org.junit.jupiter.api.Test;

public class GroupRemovalTest extends TestBase{
    @Test
    public void GroupRemovalTest() {
        openGroupsPage();
        if (!isGroupPresent()){
            createGroup("group name", "group header", "group footer");
        }
        removeGroup();
    }

}
