def imbalance(t):
    def dfs(node):
        if not node.left and not node.right:
            return 1
        l_leaves = dfs(node.left)
        r_leaves = dfs(node.right)

        return l_leaves + r_leaves

    l_leaves = dfs(t.left)
    r_leaves = dfs(t.right)

    return abs(l_leaves - r_leaves)


def min_distance(t):

    minimum = len(t)

    def dfs(node):
        if not node:
            return

        left_val = node.left.val
        right_val = node.right.val

        minimum = min(left_val, right_val, minimum)

        dfs(node.left)
        dfs(node.right)

        return

    minimum = dfs(t)
    return minimum
