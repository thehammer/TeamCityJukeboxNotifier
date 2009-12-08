package TeamCity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import jetbrains.buildServer.Build;
import jetbrains.buildServer.notification.Notificator;
import jetbrains.buildServer.notification.NotificatorRegistry;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.serverSide.UserPropertyInfo;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.vcs.VcsRoot;

public class JukeboxNotifier implements Notificator {

	public JukeboxNotifier(NotificatorRegistry notificatorRegistry) throws IOException {
        notificatorRegistry.register(this, new ArrayList<UserPropertyInfo>());
    }
	
	@Override
	public String getDisplayName() {
		return "Jukebox Notifier";
	}

	@Override
	public String getNotificatorType() {
		return "JukeboxNotifier";
	}

	private void playHammertime(String snippet_name) {
		HttpURLConnection jukebox = null;

		try {
			jukebox = (HttpURLConnection) new URL("http://10.103.180.203:3000/hammertime/add_for/" + snippet_name).openConnection();
			jukebox.setRequestMethod("POST");
			jukebox.getContent();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (jukebox != null) {
				jukebox.disconnect();
			}
		}
	}

	@Override
	public void notifyBuildFailed(SRunningBuild arg0, Set<SUser> arg1) {
		playHammertime("claire_build_failed");
	}

	@Override
	public void notifyBuildFailing(SRunningBuild arg0, Set<SUser> arg1) {
	}

	@Override
	public void notifyBuildProbablyHanging(SRunningBuild arg0, Set<SUser> arg1) {
	}

	@Override
	public void notifyBuildStarted(SRunningBuild arg0, Set<SUser> arg1) {
	}

	@Override
	public void notifyBuildSuccessful(SRunningBuild arg0, Set<SUser> arg1) {
	}

	@Override
	public void notifyLabelingFailed(Build arg0, VcsRoot arg1, Throwable arg2,
			Set<SUser> arg3) {
	}

	@Override
	public void notifyResponsibleChanged(SBuildType arg0, Set<SUser> arg1) {
	}
}
