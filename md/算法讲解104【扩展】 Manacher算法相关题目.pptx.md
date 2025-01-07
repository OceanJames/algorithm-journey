<!-- Slide number: 1 -->
# Manacher算法相关题目
左程云
前置知识
讲解098 - 乘法快速幂，本节课题目4需要
讲解103 - Manacher算法

上期视频讲了Manacher算法、扩展KMP

本期视频讲述Manacher算法相关题目，利用回文半径数组来解决问题

<!-- Slide number: 2 -->
# Manacher算法相关题目
左程云
题目1
最长回文子串
找到字符串s中最长的回文子串并返回
测试链接 : https://leetcode.cn/problems/longest-palindromic-substring/

上节课内容：
扩展串中的回文半径和真实回文长度的对应，真实长度 = p[i] - 1
扩展回文串结尾下标和真实回文串终止位置的对应，真实回文串终止位置 = 扩展回文串结尾下标 / 2

<!-- Slide number: 3 -->
# Manacher算法相关题目
左程云
题目2
回文子串数量
返回字符串s的回文子串数量
测试链接 : https://leetcode.cn/problems/palindromic-substrings/

<!-- Slide number: 4 -->
# Manacher算法相关题目
左程云
题目3
不重叠回文子串的最多数目
给定一个字符串str和一个正数k
你可以随意把str切分成多个子串
目的是找到某一种划分方案，有尽可能多的回文子串
并且每个回文子串都要求长度>=k、且彼此没有重合的部分
返回最多能划分出几个这样的回文子串
测试链接 : https://leetcode.cn/problems/maximum-number-of-non-overlapping-palindrome-substrings/

<!-- Slide number: 5 -->
# Manacher算法相关题目
左程云
题目4
长度前k名的奇数长度回文子串长度乘积
给定一个字符串s和数值k，只关心所有奇数长度的回文子串
返回其中长度前k名的回文子串的长度乘积是多少
如果奇数长度的回文子串个数不够k个，返回-1
测试链接 : https://www.luogu.com.cn/problem/P1659

<!-- Slide number: 6 -->
# Manacher算法相关题目
左程云
题目5
最长双回文串长度
输入字符串s，求s的最长双回文子串t的长度
双回文子串就是可以分成两个回文串的字符串
比如"aabb"，可以分成"aa"、"bb"
测试链接 : https://www.luogu.com.cn/problem/P4555