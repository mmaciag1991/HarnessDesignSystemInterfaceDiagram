package Components.trayNotification.notification;

public enum Notifications implements Notification {

	INFORMATION("resources/images/info.png", "#2C54AB"),
	NOTICE("resources/images/notice.png", "#8D9695"),
	SUCCESS("resources/images/success.png", "#009961"),
	WARNING("resources/images/warning.png", "#EFB300"),
	ERROR("resources/images/error.png", "#A80000");

	private final String urlResource;
	private final String paintHex;

	Notifications(String urlResource, String paintHex) {
		this.urlResource = urlResource;
		this.paintHex = paintHex;
	}

	@Override
	public String getURLResource() {
		return urlResource;
	}

	@Override
	public String getPaintHex() {
		return paintHex;
	}

}
