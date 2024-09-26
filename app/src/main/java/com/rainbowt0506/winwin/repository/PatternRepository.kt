package com.rainbowt0506.winwin.repository

import com.rainbowt0506.winwin.Constant
import com.rainbowt0506.winwin.model.HueGroup
import com.rainbowt0506.winwin.util.processPatterns

class PatternRepository {
    fun getPatterns(): List<HueGroup> {
        return processPatterns(Constant.PATTERNS)
    }
}