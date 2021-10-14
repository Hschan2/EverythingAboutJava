// Promise -> Async/await

// Bad Code
// 중첩 사용
function displayUser() {
    fetchUser()
        .then((user) => {
            fetchProfile(user)
                .then((profile) => {
                    updateUI(user, profile);
                });
        });
}

// Good Code
async function displayUser() {
    const user = await fetchUser();
    const profile = await fetchProfile(user);
    updateUI(user, profile);
}