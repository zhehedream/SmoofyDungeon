/*
 * Copyright (c) 2017 Hugo Dupanloup (Yeregorix)
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
 */

package net.smoofyuniverse.dungeon.gen.populator;

public final class ChunkInfo {
	public final int x, z;
	private long values;

	public ChunkInfo(int x, int z) {
		this.x = x;
		this.z = z;
	}

	public boolean getFlag() {
		return get(0);
	}

	private boolean get(int index) {
		return (this.values & (1L << index)) != 0;
	}

	public void setFlag(boolean value) {
		set(0, value);
	}

	private void set(int index, boolean value) {
		if (value)
			this.values |= (1L << index);
		else
			this.values &= ~(1L << index);
	}

	public boolean getFlag(int layer) {
		return get(index(layer));
	}

	private int index(int layer) {
		if (layer < 0 || layer > 6)
			throw new IllegalArgumentException("layer");
		return layer + 1;
	}

	public void setFlag(int layer, boolean value) {
		set(index(layer), value);
	}

	public boolean getFlag(int layer, int room) {
		return get(index(layer, room));
	}

	private int index(int layer, int room) {
		if (layer < 0 || layer > 6)
			throw new IllegalArgumentException("layer");
		if (room < 0 || room > 3)
			throw new IllegalArgumentException("room");
		return layer * 4 + room + 8;
	}

	public void setFlag(int layer, int room, boolean value) {
		set(index(layer, room), value);
	}
}