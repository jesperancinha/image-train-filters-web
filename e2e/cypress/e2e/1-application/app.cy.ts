describe('empty spec', () => {
  it('passes', () => {
    cy.visit("/")
    cy.get('a').contains('Contour').click({force:true});
    cy.get('a').contains('Kuwahara').click({force:true});
    cy.get('a').contains('Chartizate').click({force:true});
  })
})