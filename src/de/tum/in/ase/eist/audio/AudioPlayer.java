package de.tum.in.ase.eist.audio;

import java.net.URL;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class handles the background music played during the game using a JavaFX {@link MediaPlayer}.
 */
public class AudioPlayer implements AudioPlayerInterface {

	private static final String BACKGROUND_MUSIC_FILE = "Music.mp3";
	private static final String CRASH_SOUND_FILE = "Fail_sound.mp3";
	private static final String LEVEL_UP_SOUND_FILE = "level_up.mp3";
	private static final String EXPLOSION_SOUND_FILE = 	"explosion.mp3";

	private static final double CRASH_SOUND_VOLUME = 0.9;
	private static final double LEVEL_UP_SOUND_VOLUME = 0.9;
	private static final double EXPLOSION_SOUND_VOLUME = 0.9;



	private final MediaPlayer musicPlayer;
	private final AudioClip crashPlayer;
	private final AudioClip levelUpPlayer;
	private final AudioClip explosionPlayer;

	/**
	 * Constructs a new AudioPlayer by directly loading the background music and
	 * crash sound files into a new MediaPlayer / AudioClip.
	 */
	public AudioPlayer() {
		this.musicPlayer = new MediaPlayer(loadAudioFile(BACKGROUND_MUSIC_FILE));
		this.crashPlayer = new AudioClip(convertNameToUrl(CRASH_SOUND_FILE));
		this.levelUpPlayer = new AudioClip(convertNameToUrl(LEVEL_UP_SOUND_FILE));
		this.explosionPlayer = new AudioClip(convertNameToUrl(EXPLOSION_SOUND_FILE));
	}

	@Override
	public void playBackgroundMusic() {
		if (isPlayingBackgroundMusic()) {
			return;
		}
		// Loop for the main music sound:
		this.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		this.musicPlayer.play();
	}

	@Override
	public void stopBackgroundMusic() {
		if (isPlayingBackgroundMusic()) {
			this.musicPlayer.stop();
		}
	}

	@Override
	public boolean isPlayingBackgroundMusic() {
		return MediaPlayer.Status.PLAYING.equals(this.musicPlayer.getStatus());
	}

	@Override
	public void playCrashSound() {
		crashPlayer.play(CRASH_SOUND_VOLUME);
	}

	@Override
	public void playLevelUp(){
		levelUpPlayer.play(LEVEL_UP_SOUND_VOLUME);
	}

	@Override
	public void playExplosion(){
		explosionPlayer.play(EXPLOSION_SOUND_VOLUME);
	}

	private Media loadAudioFile(String fileName) {
		return new Media(convertNameToUrl(fileName));
	}

	private String convertNameToUrl(String fileName) {
		URL musicSourceUrl = getClass().getClassLoader().getResource(fileName);
		if (musicSourceUrl == null) {
			throw new IllegalArgumentException(
					"Please ensure that your resources folder contains the appropriate files for this exercise.");
		}
		return musicSourceUrl.toExternalForm();
	}

	public static String getBackgroundMusicFile() {
		return BACKGROUND_MUSIC_FILE;
	}

	public static String getCrashSoundFile() {
		return CRASH_SOUND_FILE;
	}
}
