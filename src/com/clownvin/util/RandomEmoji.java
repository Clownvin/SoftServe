package com.clownvin.util;

public final class RandomEmoji {
    public static final String[] EMOJIS =
            {"🍕", "🍔", "🍩", "🍻", "🥃", "🥪", "🍜",
  "🥞", "🥓", "🌯", "🍙", "🥗", "🍨", "🍫", "☕", "🌭", "🌮", "🍗", "🍟", "🍎", "🍊", "🍉",
  "🍈", "🍄", "🍅", "🍆", "🍇", "🍋", "🍌", "🍍", "🍏",
  "🍐", "🍑", "🍒", "🍓", "🍘", "🍚", "🍛", "🍝", "🍞",
  "🍠", "🍡", "🍢", "🍣", "🍤", "🍥", "🍦", "🍧", "🍪",
  "🍬", "🍭", "🍮", "🍯", "🍰", "🍱", "🍲", "🍳", "🍴",
  "🍵", "🍶", "🍷", "🍸", "🍹", "🍺", "🍾", "🍿", "🎂",
  "🥂", "🥐", "🥑", "🥒", "🥔", "🥕", "🥖", "🥘", "🥙",
  "🥚", "🥛", "🥜", "🥝", "🥟", "🥠", "🥡", "🥢", "🥣",
  "🥥", "🥧", "🥨", "🥩", "🥫", "🧀", "🌰", "🌽"};

    public static String get() {
        return EMOJIS[(int) (Math.random() * EMOJIS.length)];
    }

    public static String emojify(String string) {
        String emoji = get();
        return emoji + " " + string + " " + emoji;
    }
}
