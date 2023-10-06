package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase{

    public GroupHelper(ApplicationManager manager){
        super(manager);
    }

    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        RemoveSelectedGroups();
        returnToGroupsPage();
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifedGroup) {
        openGroupsPage();
        selectGroup();
        InitGroupModification();
        fillGroupForm(modifedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }
    public void openGroupsPage() {
      if(! manager.isElementPresent(By.name("new"))){
          click(By.linkText("groups"));
      }
    }


    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }



    private void RemoveSelectedGroups() {
        click(By.name("delete"));
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void InitGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.name("selected[]"));
    }

    public int getCount() {
        openGroupsPage();
        return  manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        selectAllGroups();
        RemoveSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
}
