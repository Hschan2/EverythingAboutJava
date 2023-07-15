const userLocale = navigator.language;
const currentTime = new Date().toLocaleTimeString(userLocale);

chrome.runtime.onInstalled.addListener(() => {
    console.log(`Current time: ${currentTime}. You are now using a Pick Color`);
});

