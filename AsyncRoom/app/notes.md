

    private void getAllUsers() {
        userDao.getAllUsers().subscribeOn(scheduler).subscribe(users -> {
                    Log.i("Users: ", "All Users: " + users.toString());
                    userDetailsTextView.setText(users.stream().map(Objects::toString).collect(Collectors.joining("\n")));

                }, throwable -> Log.e("Users: ", "Exception while fetching all users", throwable),
                () -> Log.i("Users: ", "Finished finding users"));
    }
    
    
    
        private void addUser(String firstName, String lastName) {
            userDao.insert(new User(firstName, lastName))
                    .subscribeOn(scheduler)
                    .subscribe(() -> Log.i("Users: ", "Inserted User: " + firstName + " " + lastName),
                            (e) -> Log.e("Users: ", "Exception while adding user", e)).dispose();
        }
    
        private void deleteUser(String id) {
            if (id != null && id.trim().matches(NUMBER_REGEX)) {
                userDao.getUserById(Integer.parseInt(id)).singleElement().subscribeOn(scheduler).subscribe((user -> {
                    Log.i("Users: ", "Found the user: " + user.toString());
                    userDao.delete(user).subscribeOn(scheduler)
                            .subscribe(() -> Log.i("Users: ", "Deleted user with id: " + id),
                                    throwable -> Log.e("Users: ", "Exception while deleting user with id: " + id, throwable))
                            .dispose();
                }), throwable -> Log.e("Users: ", "User with ID: " + id + " not found", throwable)).dispose();
            } else {
                Log.i("Users: ", "Invalid userId: " + id);
            }
        }
        
        
        
        
        
        

    private Flowable<List<User>> getAllUsers() {
        return userDao.getAllUsers().subscribeOn(scheduler);
    }
        
        
        
        
        getAllUsers().subscribe(users -> {
                    Log.i("Users: ", "All Users: " + users.toString());
                    userDetailsTextView.setText(users.stream().map(Objects::toString).collect(Collectors.joining("\n")));
                }, throwable -> Log.e("Users: ", "Exception while fetching all users", throwable),
                () -> Log.i("Users: ", "Finished finding users")).dispose();
        
        
        
        
        