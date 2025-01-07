<!-- Slide number: 1 -->
# Morris遍历
左程云
前置知识
讲解017、讲解018 - 递归方式遍历二叉树、非递归方式遍历二叉树

之前讲过的二叉树三种序的遍历
不管是递归还是非递归的方式，时间复杂度为O(n)，额外空间复杂度为O(树高)

Morris遍历解决的问题
二叉树三种序的遍历，时间复杂度为O(n)，额外空间复杂度为O(1)
核心在于利用二叉树自身的空闲指针

大厂笔试、比赛时，一般不会用到
大厂面试时，一道普通的二叉树题目，可以先给出普通解法，
然后和面试官聊Morris遍历的解法，可以增加印象分(装逼用)
工程上很多场合，内存空间非常有限，遍历二叉树时可以使用这种遍历

<!-- Slide number: 2 -->
# Morris遍历
左程云
Morris遍历的理解核心
没有左子树的节点只到达一次，有左子树的节点会到达两次
利用左子树最右节点的右指针状态，来标记是第几次到达

Morris遍历的过程，课上重点图解
1，开始时cur来到头节点，cur为空时过程停止
2，如果cur没有左孩子，cur向右移动
3，如果cur有左孩子，找到cur左子树的最右节点mostRight
   A，如果mostRight的右指针指向空，让其指向cur，然后cur向左移动
   B，如果mostRight的右指针指向cur，让其指向null，然后cur向右移动

额外空间复杂度很明显是O(1)，但是时间复杂度依然为O(n)，课上重点图解

<!-- Slide number: 3 -->
# Morris遍历
左程云
题目1
Morris遍历实现先序和中序遍历
测试链接 : https://leetcode.cn/problems/binary-tree-preorder-traversal/
测试链接 : https://leetcode.cn/problems/binary-tree-inorder-traversal/

<!-- Slide number: 4 -->
# Morris遍历
左程云
题目2
Morris遍历实现后序遍历
测试链接 : https://leetcode.cn/problems/binary-tree-postorder-traversal/

Morris遍历实现后序遍历，需要树上链表的翻转，课上重点图解

<!-- Slide number: 5 -->
# Morris遍历
左程云
重要提醒
用递归方式遍历二叉树能解决的问题，并不一定都可以用Morris遍历解决

递归方式遍历二叉树，每个节点都能到达3次，
所以每棵子树都能收集左子树信息、右子树信息，然后整合出整棵子树的信息

Morris方式遍历二叉树，每个节点最多到达2次，
所以信息整合的强度不及递归的方式

具体哪些问题能被Morris遍历解决，需要具体问题具体分析
如果必须回到一个节点3次，问题才能得到解决，那么这样的问题无法用Morris遍历解决

<!-- Slide number: 6 -->
# Morris遍历
左程云
题目3
Morris遍历判断搜索二叉树
测试链接 : https://leetcode.cn/problems/validate-binary-search-tree/

<!-- Slide number: 7 -->
# Morris遍历
左程云
题目4
Morris遍历求二叉树最小高度
测试链接 : https://leetcode.cn/problems/minimum-depth-of-binary-tree/

<!-- Slide number: 8 -->
# Morris遍历
左程云
题目5
Morris遍历求两个节点的最低公共祖先
测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/