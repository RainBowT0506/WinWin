package com.rainbowt0506.winwin.util

import com.rainbowt0506.winwin.model.HueGroup
import com.rainbowt0506.winwin.model.PatternDto

fun processPatterns(patterns: List<PatternDto>): List<HueGroup> {
    return patterns
        .filter { it.level > 100 }  // 過濾掉 level <= 100 的項目
        .groupBy { it.hue }  // 根據 hue 分組
        .toSortedMap()  // 依照 hue 的字母順序排序
        .map { (hue, patternList) ->
            HueGroup(hue, patternList.sortedBy { it.level })  // 對每組按 level 排序
        }
}