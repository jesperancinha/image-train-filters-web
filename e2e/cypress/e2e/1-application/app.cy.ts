describe('empty spec', () => {
  it('passes', () => {
    cy.visit("/")
    cy.get('a').contains('Contour').parent().click();
    cy.get('a').contains('Kuwahara').parent().parent().click();
    cy.get('a').contains('Chartizate').parent().parent().click();
  })
})