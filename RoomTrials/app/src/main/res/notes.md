// Get notified only about unique events
Flowable<User> = getUserById(id).distinctUntilChanged()