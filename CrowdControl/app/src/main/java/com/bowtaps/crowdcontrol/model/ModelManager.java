package com.bowtaps.crowdcontrol.model;

import java.util.Date;
import java.util.List;

/**
 * A class dedicated for providing model functionality that does not belong in any individual
 * model, such as logging users in, signing up new users, or getting the current user.
 *
 * @author Daniel Andrus
 * @since 2016-01-17
 */
public interface ModelManager {

    public UserModel logInUser(String username, String password) throws Exception;

    public void logInUserInBackground(String username, String password, final BaseModel.LoadCallback callback);

    public UserModel createUser(String username, String email, String password) throws Exception;

    public void createUserInBackground(String username, String email, String password, final BaseModel.SaveCallback callback);

    public UserModel getCurrentUser();

    public Boolean logOutCurrentUser() throws Exception;

    public List<? extends GroupModel> fetchAllGroups() throws Exception;

    public void fetchAllGroupsInBackground(final BaseModel.FetchCallback callback);

    public GroupModel getCurrentGroup();

    public void setCurrentGroup(GroupModel group);

    public GroupModel fetchCurrentGroup() throws Exception;

    public void fetchCurrentGroupInBackground(final BaseModel.LoadCallback callback);

    public GroupModel createGroup(UserProfileModel leader, String name, String description) throws Exception;

    public void createGroupInBackground(UserProfileModel leader, String name, String description, final BaseModel.SaveCallback callback);

    public List<? extends BaseModel> fetchGroupUpdates(GroupModel group, UserProfileModel user, Date since) throws Exception;
}
