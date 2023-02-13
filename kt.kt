fun rabinKarpSearch(text: String, pattern: String): Int {
    val m = pattern.length
    val n = text.length
    val d = 256
    val q = 101
    var h = 1
    var p = 0
    var t = 0
    var result = -1

    // Calculate h = d^(m-1) % q
    for (i in 0 until m - 1) {
        h = (h * d) % q
    }

    // Calculate the hash values of pattern and first window of text
    for (i in 0 until m) {
        p = (d * p + pattern[i].toInt()) % q
        t = (d * t + text[i].toInt()) % q
    }

    // Slide the pattern over text one by one
    for (i in 0 until n - m + 1) {
        // Check the hash values of current window of text and pattern
        if (p == t) {
            var j = 0
            for (j in 0 until m) {
                if (text[i + j] != pattern[j]) {
                    break
                }
            }

            // If all characters are same
            if (j == m - 1) {
                result = i
                break
            }
        }

        // Calculate hash value for next window of text: remove leading digit, add trailing digit
        if (i < n - m) {
            t = (d * (t - text[i].toInt() * h) + text[i + m].toInt()) % q

            // We might get negative value of t, converting it to positive
            if (t < 0) {
                t = (t + q)
            }
        }
    }

    return result
}
