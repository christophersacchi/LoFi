/*
 * MIT License
 * 
 * Copyright (c) 2017 Christopher Sacchi
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */

package com.csharpemcee.lofi;

import ddf.minim.AudioOutput;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioRecorder;
import ddf.minim.Minim;
import ddf.minim.ugens.BitCrush;
import ddf.minim.ugens.FilePlayer;
import processing.core.PApplet;

public class LoFi extends PApplet {
	Minim minim;
	FilePlayer player;
	BitCrush crusher;
	AudioOutput out;
	AudioRecorder recorder;
	
	public void setup() {
		background(255);
		minim = new Minim(this);
		minim.debugOn();
		crusher = new BitCrush(6.0f, 8000);
		out = minim.getLineOut(Minim.MONO);
		player = new FilePlayer(minim.loadFileStream("file.mp3", 1024, true));
		recorder = minim.createRecorder(out, "crushed.wav");
		recorder.beginRecord();
		player.patch(crusher);
		crusher.patch(out);
		
		while (recorder.isRecording());
		recorder.endRecord();
	}

	public void draw() {
	}
	
	public void settings() {
		size(800, 600);
	}
	
	public static void main(String[] args) {
		PApplet.main("com.csharpemcee.lofi.LoFi");
	}
}
