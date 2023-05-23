class Pair<T, F> {
  Pair(this.left, this.right);

  final T left;
  final F right;

  @override
  String toString() => 'Pair[$left, $right]';
}